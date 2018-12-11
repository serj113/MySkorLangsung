package com.setia.myfootballmatch.fragment.player

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setia.myfootballmatch.R
import com.setia.myfootballmatch.helper.FootballClient

import com.setia.myfootballmatch.model.Player
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PlayerFragment : Fragment() {

    private var listener: OnListPlayer? = null

    private var teamId: String = ""
    lateinit var myAdapter: MyplayerRecyclerViewAdapter
    private var mView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            teamId = it.getString(ARG_TEAM_ID)
        }

        FootballClient.sharedInstance.get().getAllTeamPlayer(teamId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    myAdapter.mValues = it.players?.toMutableList() ?: ArrayList()
                    mView?.let {
                        myAdapter.notifyDataSetChanged()
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
        mView = inflater.inflate(R.layout.fragment_player_list, container, false)

        myAdapter = MyplayerRecyclerViewAdapter(listener)

        val view = mView
        if (view is RecyclerView && view != null) {
            with(view) {
                layoutManager = android.support.v7.widget.LinearLayoutManager(context)
                adapter = myAdapter
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListPlayer) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListPlayer")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        mView = null
    }

    interface OnListPlayer {
        fun onTapPlayer(item: Player?)
    }

    companion object {

        const val ARG_TEAM_ID = "team_id"

        @JvmStatic
        fun newInstance(teamId: String) =
                PlayerFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_TEAM_ID, teamId)
                    }
                }
    }

}
