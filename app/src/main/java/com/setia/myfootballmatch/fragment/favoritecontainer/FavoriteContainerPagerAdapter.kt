package com.setia.myfootballmatch.fragment.favoritecontainer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.setia.myfootballmatch.fragment.event.MatchListFragment
import com.setia.myfootballmatch.fragment.event.Schedule
import com.setia.myfootballmatch.fragment.eventfavorite.EventFavoriteFragment
import com.setia.myfootballmatch.fragment.teamfavorite.TeamFavoriteFragment

class FavoriteContainerPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return EventFavoriteFragment.newInstance()
            else -> {
                return TeamFavoriteFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "MATCHES"
            1 -> return "TEAMS"
            else -> {
                return null
            }
        }
    }
}