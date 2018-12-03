package com.setia.myfootballmatch.fragment.team

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.setia.myfootballmatch.R

import com.setia.myfootballmatch.model.Team
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_team_item.view.*

class MyTeamRecyclerViewAdapter(
        private val mListener: TeamListFragment.TeamInteractionListener?)
    : RecyclerView.Adapter<MyTeamRecyclerViewAdapter.ViewHolder>() {

    var mValues: MutableList<Team> = ArrayList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Team
            mListener?.onTapTeam(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_team_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.teamName.text = item.strTeam ?: ""
        Picasso.get().load(item.strTeamLogo).into(holder.teamLogo)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val teamLogo: ImageView = mView.team_iv
        val teamName: TextView = mView.team_name_tv
    }
}
