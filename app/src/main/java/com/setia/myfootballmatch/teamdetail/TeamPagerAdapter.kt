package com.setia.myfootballmatch.teamdetail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.setia.myfootballmatch.fragment.overview.OverviewFragment
import com.setia.myfootballmatch.fragment.player.PlayerFragment
import com.setia.myfootballmatch.model.Team

class TeamPagerAdapter(fm: FragmentManager,val team: Team) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return OverviewFragment.newInstance(team.strDescriptionEN ?: "")
            else -> {
                return OverviewFragment.newInstance(team.strDescriptionEN ?: "")
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "OVERVIEW"
            1 -> return "PLAYERS"
            else -> {
                return null
            }
        }
    }
}