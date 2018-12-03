package com.setia.myfootballmatch.fragment.team

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import com.setia.myfootballmatch.R
import com.setia.myfootballmatch.helper.FootballClient
import com.setia.myfootballmatch.helper.LeagueArrayAdapter
import com.setia.myfootballmatch.model.League

import com.setia.myfootballmatch.model.Team
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.find

class TeamListFragment : Fragment() {
    private var listener: TeamInteractionListener? = null

    private var leagues: MutableList<League> = mutableListOf()

    private lateinit var spinner: Spinner

    lateinit var myAdapter: MyTeamRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    myAdapter.mValues = it.teams?.toMutableList() ?: ArrayList()
                    myAdapter.notifyDataSetChanged()
                }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                TeamListFragment()
    }
}
