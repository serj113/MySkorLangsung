package com.setia.myfootballmatch.fragment.teamfavorite

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setia.myfootballmatch.R
import com.setia.myfootballmatch.helper.FootballClient

import com.setia.myfootballmatch.helper.database
import com.setia.myfootballmatch.model.Team
import com.setia.myfootballmatch.model.TeamFavorite
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class TeamFavoriteFragment : Fragment() {
    private var listener: TeamFavoriteInteractionListener? = null

    lateinit var myAdapter: MyTeamFavoriteRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context?.database?.use {
            val result = select(TeamFavorite.TABLE_TEAM)
            val favorite = result.parseList(classParser<TeamFavorite>())
            fetchFavorite(favorite)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team_favorite_list, container, false)

        myAdapter = MyTeamFavoriteRecyclerViewAdapter(listener)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = android.support.v7.widget.LinearLayoutManager(context)
                adapter = myAdapter
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TeamFavoriteInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement TeamFavoriteInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface TeamFavoriteInteractionListener {
        fun onTapTeamFavorite(item: Team?)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                TeamFavoriteFragment()
    }

    private fun fetchFavorite(favorites: List<TeamFavorite>) {
        for (favorite in favorites) {
            FootballClient.sharedInstance.get().getTeamDetail(favorite.teamId ?: "")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        myAdapter.mValues.add(it.teams?.first() ?: Team())
                        myAdapter.notifyDataSetChanged()
                    }
        }
    }
}
