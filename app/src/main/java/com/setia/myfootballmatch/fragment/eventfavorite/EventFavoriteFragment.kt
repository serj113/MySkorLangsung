package com.setia.myfootballmatch.fragment.eventfavorite

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
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

class EventFavoriteFragment : Fragment() {

    private var listener: EventFavoriteInteractionListener? = null

    lateinit  var myAdapter: MyEventFavoriteRecyclerViewAdapter

    private var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_event_favorite_list, container, false)

        myAdapter = MyEventFavoriteRecyclerViewAdapter(listener)

        val view = mView
        if (view is RecyclerView && view != null) {
            with(view) {
                layoutManager = android.support.v7.widget.LinearLayoutManager(context)
                adapter = myAdapter
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        context?.database?.use {
            val result = select(Favorite.TABLE_EVENT)
            val favorite = result.parseList(classParser<Favorite>())
            myAdapter.mValues.clear()
            fetchFavorite(favorite)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EventFavoriteInteractionListener) {
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

    interface EventFavoriteInteractionListener {
        fun onTapEventFavorite(item: Event?)
    }

    companion object {
        fun newInstance() =
                EventFavoriteFragment()
    }

    private fun fetchFavorite(favorites: List<Favorite>) {
        for (favorite in favorites) {
            FootballClient.sharedInstance.get().getEventDetail(favorite.eventId ?: "")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        myAdapter.mValues.add(it.events?.first() ?: Event())
                        val view = mView
                        if (view != null) {
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
