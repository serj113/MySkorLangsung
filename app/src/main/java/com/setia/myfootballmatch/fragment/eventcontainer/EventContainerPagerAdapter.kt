package com.setia.myfootballmatch.fragment.eventcontainer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.setia.myfootballmatch.fragment.event.MatchListFragment
import com.setia.myfootballmatch.fragment.event.Schedule

class EventContainerPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MatchListFragment.newInstance(Schedule.NEXT)
            else -> {
                return MatchListFragment.newInstance(Schedule.PAST)
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "NEXT"
            1 -> return "LAST"
            else -> {
                return null
            }
        }
    }
}