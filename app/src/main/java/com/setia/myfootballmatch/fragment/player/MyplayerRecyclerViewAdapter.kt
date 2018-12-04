package com.setia.myfootballmatch.fragment.player

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.setia.myfootballmatch.R

import com.setia.myfootballmatch.model.Player
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_player_item.view.*

class MyplayerRecyclerViewAdapter(
        private val mListener: PlayerFragment.OnListPlayer?)
    : RecyclerView.Adapter<MyplayerRecyclerViewAdapter.ViewHolder>() {

    var mValues: MutableList<Player> = ArrayList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Player
            mListener?.onTapPlayer(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_player_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.playerName.text = item.strPlayer ?: ""
        holder.playerPosition.text = item.strPosition ?: ""
        Picasso.get().load(item.strCutout).into(holder.playerImage)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val playerImage: ImageView = mView.player_iv
        val playerName: TextView = mView.player_name_tv
        val playerPosition: TextView = mView.player_position_tv
    }
}
