package com.setia.myfootballmatch.fragment.event

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Spinner
import com.setia.myfootballmatch.R
import com.setia.myfootballmatch.helper.FootballClient
import com.setia.myfootballmatch.helper.LeagueArrayAdapter
import com.setia.myfootballmatch.helper.database
import com.setia.myfootballmatch.model.Event
import com.setia.myfootballmatch.model.Favorite
import com.setia.myfootballmatch.model.League
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MatchListFragment.OnListFragmentInteractionListener] interface.
 */
class MatchListFragment : Fragment(), SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    private var schedule = Schedule.FAVORITE

    private var listener: OnListFragmentInteractionListener? = null

    private var leagues: MutableList<League> = mutableListOf()

    private var events: MutableList<Event> = mutableListOf()

    private lateinit var spinner: Spinner

    lateinit  var myAdapter: MyMatchRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            schedule = it.getSerializable(ARG_COLUMN_SCHEDULE) as Schedule
        }

        FootballClient.sharedInstance.get().getAllLeague()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    leagues = it.leagues?.toMutableList() ?: mutableListOf()
                    updateSpinner()
                }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_match, container, false)

        spinner = view.find(R.id.match_sp)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateEvent(leagues[position].idLeague ?: "0")
            }

        }

        val recyleView = view.find<RecyclerView>(R.id.match_rv)
        myAdapter = MyMatchRecyclerViewAdapter(listener)
        recyleView.layoutManager = android.support.v7.widget.LinearLayoutManager(context)
        recyleView.adapter = myAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun updateSpinner() {
        val spinnerAdapter = LeagueArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                values = leagues.toList()
        )
        spinner.adapter = spinnerAdapter
    }

    private fun updateEvent(eventId: String) {
        when (schedule) {
            Schedule.NEXT -> {
                FootballClient.sharedInstance.get().getNextSchedule(eventId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            myAdapter.isHideCalendar = false
                            events = it.events?.toMutableList() ?: ArrayList()
                            myAdapter.mValues = events
                            myAdapter.notifyDataSetChanged()
                        }
            }
            Schedule.PAST -> {
                FootballClient.sharedInstance.get().getPastSchedule(eventId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            myAdapter.isHideCalendar = true
                            events = it.events?.toMutableList() ?: ArrayList()
                            myAdapter.mValues = events
                            myAdapter.notifyDataSetChanged()
                        }
            }
        }
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Event?)
        fun addToCalendar(item: Event?)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setQueryHint("Search")

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0 == null || p0.trim().isEmpty()) {
            resetSearch()
            return false
        }

        val filteredValues = events.filter {
            it.strHomeTeam?.contains(p0, ignoreCase = true) ?: false
            || it.strAwayTeam?.contains(p0, ignoreCase = true) ?: false
        }
        myAdapter.mValues = filteredValues.toMutableList()
        myAdapter.notifyDataSetChanged()

        return false
    }

    private fun resetSearch() {
        myAdapter.mValues = events
        myAdapter.notifyDataSetChanged()
    }

    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
        return true
    }

    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        return true
    }

    companion object {

        const val ARG_COLUMN_SCHEDULE = "column-schedule"

        @JvmStatic
        fun newInstance(schedule: Schedule) =
                MatchListFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_COLUMN_SCHEDULE, schedule)
                    }
                }
    }
}

enum class Schedule {
    PAST, NEXT, FAVORITE
}
