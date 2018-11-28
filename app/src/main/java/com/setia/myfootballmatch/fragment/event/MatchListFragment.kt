package com.setia.myfootballmatch.fragment.event

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
import com.setia.myfootballmatch.model.Event
import com.setia.myfootballmatch.model.Favorite
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MatchListFragment.OnListFragmentInteractionListener] interface.
 */
class MatchListFragment : Fragment() {

    private var schedule = Schedule.FAVORITE

    private var listener: OnListFragmentInteractionListener? = null

    lateinit  var myAdapter: MyMatchRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            schedule = it.getSerializable(ARG_COLUMN_SCHEDULE) as Schedule
        }

        when (schedule) {
            Schedule.NEXT -> {
                FootballClient.sharedInstance.get().getNextSchedule("4328")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            myAdapter.mValues = it.events?.toMutableList() ?: ArrayList()
                            myAdapter.notifyDataSetChanged()
                        }
            }
            Schedule.PAST -> {
                FootballClient.sharedInstance.get().getPastSchedule("4328")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            myAdapter.mValues = it.events?.toMutableList() ?: ArrayList()
                            myAdapter.notifyDataSetChanged()
                        }
            }
            Schedule.FAVORITE -> {
                context?.database?.use {
                    val result = select(Favorite.TABLE_EVENT)
                    val favorite = result.parseList(classParser<Favorite>())
                    fetchFavorite(favorite)
                }
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_match_list, container, false)

        myAdapter = MyMatchRecyclerViewAdapter(listener)

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

    private fun fetchFavorite(favorites: List<Favorite>) {
        for (favorite in favorites) {
            FootballClient.sharedInstance.get().getEventDetail(favorite.eventId ?: "")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        myAdapter.mValues.add(it.events?.first() ?: Event())
                        myAdapter.notifyDataSetChanged()
                    }
        }
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Event?)
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
