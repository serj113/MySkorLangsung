package com.setia.myfootballmatch.fragment.team

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Spinner
import com.setia.myfootballmatch.R
import com.setia.myfootballmatch.helper.FootballClient
import com.setia.myfootballmatch.helper.LeagueArrayAdapter
import com.setia.myfootballmatch.model.League

import com.setia.myfootballmatch.model.Team
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.find

class TeamListFragment : Fragment(), SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {
    private var listener: TeamInteractionListener? = null

    private var leagues: MutableList<League> = mutableListOf()
    private var teams: MutableList<Team> = mutableListOf()

    private lateinit var spinner: Spinner

    lateinit var myAdapter: MyTeamRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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
        val view = inflater.inflate(R.layout.fragment_team, container, false)

        spinner = view.find(R.id.team_sp)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateTeam(leagues[position].idLeague ?: "0")
            }

        }

        val recyleView = view.find<RecyclerView>(R.id.team_rv)
        myAdapter = MyTeamRecyclerViewAdapter(listener)
        recyleView.layoutManager = android.support.v7.widget.LinearLayoutManager(context)
        recyleView.adapter = myAdapter

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TeamInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement TeamInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface TeamInteractionListener {
        fun onTapTeam(item: Team?)
    }


    private fun updateSpinner() {
        val spinnerAdapter = LeagueArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                values = leagues.toList()
        )
        spinner.adapter = spinnerAdapter
    }

    private fun updateTeam(eventId: String) {
        FootballClient.sharedInstance.get().getAllTeam(eventId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    teams = it.teams?.toMutableList() ?: ArrayList()
                    myAdapter.mValues = teams
                    myAdapter.notifyDataSetChanged()
                }
    }override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
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

        val filteredValues = teams.filter { it.strTeam?.contains(p0, ignoreCase = true) ?: false }
        myAdapter.mValues = filteredValues.toMutableList()
        myAdapter.notifyDataSetChanged()

        return false
    }

    private fun resetSearch() {
        myAdapter.mValues = teams
        myAdapter.notifyDataSetChanged()
    }

    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
        return true
    }

    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                TeamListFragment()
    }
}
