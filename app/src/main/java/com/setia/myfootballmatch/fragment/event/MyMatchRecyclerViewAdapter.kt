package com.setia.myfootballmatch.fragment.event

import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.setia.myfootballmatch.R


import com.setia.myfootballmatch.fragment.event.MatchListFragment.OnListFragmentInteractionListener
import com.setia.myfootballmatch.model.Event

import kotlinx.android.synthetic.main.fragment_match_item.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MyMatchRecyclerViewAdapter(
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyMatchRecyclerViewAdapter.ViewHolder>() {

    var isHideCalendar: Boolean = false
    var mValues: MutableList<Event> = ArrayList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Event
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_match_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        val date = convertDate(item.dateEvent ?: "", item.strTime ?: "")
        holder.team1.text = item.strHomeTeam

        holder.team1Score.text = if (item.intHomeScore != null) item.intHomeScore.toString() else ""
        holder.team2Score.text = if (item.intAwayScore != null) item.intAwayScore.toString() else ""
        holder.team2.text = item.strAwayTeam
        holder.date.text = SimpleDateFormat("EEE, dd MMM yyyy").format(date)
        holder.time.text = SimpleDateFormat("hh:mm a").format(date)
        if (isHideCalendar) {
            holder.calendar.visibility = View.INVISIBLE
        } else {
            holder.calendar.visibility = View.VISIBLE
            holder.calendar.setOnClickListener {
                mListener?.addToCalendar(item, date)
            }
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    private fun convertDate(date: String, time: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val covertedDate = "$date $time"
        return formatter.parse(covertedDate)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val team1Score: TextView = mView.team_1_score_tv
        val team1: TextView = mView.team_1_tv
        val team2Score: TextView = mView.team_2_score_tv
        val team2: TextView = mView.team_2_tv
        val date: TextView = mView.date_tv
        val time: TextView = mView.time_tv
        val calendar: ImageButton = mView.calendar
    }

}
