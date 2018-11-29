package com.setia.myfootballmatch.teamdetail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TeamPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MatchListFragment.newInstance(Schedule.PAST)
            1 -> return MatchListFragment.newInstance(Schedule.NEXT)
            else -> {
                return MatchListFragment.newInstance(Schedule.EMPTY)
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Last Match"
            1 -> return "Next Match"
            else -> {
                return null
            }
        }
    }
}