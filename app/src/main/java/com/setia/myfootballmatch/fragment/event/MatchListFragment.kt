package com.setia.myfootballmatch.fragment.event

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
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
import java.util.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MatchListFragment.OnListFragmentInteractionListener] interface.
 */
class MatchListFragment : Fragment() {

    private var schedule = Schedule.NEXT

    private var listener: OnListFragmentInteractionListener? = null

    private var leagues: MutableList<League> = mutableListOf()

    private var events: MutableList<Event> = mutableListOf()

    private lateinit var spinner: Spinner

    private var mView: View? = null

    lateinit  var myAdapter: MyMatchRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            schedule = it.getSerializable(ARG_COLUMN_SCHEDULE) as Schedule
        }

        FootballClient.sharedInstance.get().getAllLeague()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    leagues = it.leagues?.toMutableList() ?: mutableListOf()
                    val view = mView
                    if (view != null) {
                        if (leagues.isEmpty()) {
                            Snackbar.make(view, "Data Kosong", Snackbar.LENGTH_SHORT).show()
                        }
                        updateSpinner()
                    }
                }, {
                    val view = mView
                    if (view != null) {
                        Snackbar.make(view, "Gagal Mengambil Data", Snackbar.LENGTH_SHORT).show()
                    }
                })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_match, container, false)

        val view = mView
        if (view != null) {
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
        }

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
        mView = null
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
                        .subscribe({
                            events = it.events?.toMutableList() ?: ArrayList()
                            val view = mView
                            if (view != null) {
                                if (events.isEmpty()) {
                                    Snackbar.make(view, "Data Kosong", Snackbar.LENGTH_SHORT).show()
                                }
                                myAdapter.isHideCalendar = false
                                myAdapter.mValues = events
                                myAdapter.notifyDataSetChanged()
                            }
                        }, {
                            val view = mView
                            if (view != null) {
                                Snackbar.make(view, "Gagal Mengambil Data", Snackbar.LENGTH_SHORT).show()
                            }
                        })
            }
            Schedule.PAST -> {
                FootballClient.sharedInstance.get().getPastSchedule(eventId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            events = it.events?.toMutableList() ?: ArrayList()
                            val view = mView
                            if (view != null) {
                                if (events.isEmpty()) {
                                    Snackbar.make(view, "Data Kosong", Snackbar.LENGTH_SHORT).show()
                                }
                                myAdapter.isHideCalendar = true
                                myAdapter.mValues = events
                                myAdapter.notifyDataSetChanged()
                            }
                        }, {
                            val view = mView
                            if (view != null) {
                                Snackbar.make(view, "Gagal Mengambil Data", Snackbar.LENGTH_SHORT).show()
                            }
                        })
            }
        }
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Event?)
        fun addToCalendar(item: Event?, date: Date?)
        fun openSearchActivity()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.lup_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                listener?.openSearchActivity()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
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
    PAST, NEXT
}
