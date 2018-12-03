package com.setia.myfootballmatch

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.setia.myfootballmatch.eventdetail.EventDetailActivity
import com.setia.myfootballmatch.fragment.event.MatchListFragment
import com.setia.myfootballmatch.fragment.event.Schedule
import com.setia.myfootballmatch.fragment.eventcontainer.EventContainerFragment
import com.setia.myfootballmatch.fragment.eventfavorite.EventFavoriteFragment
import com.setia.myfootballmatch.fragment.favoritecontainer.FavoriteContainerFragment
import com.setia.myfootballmatch.fragment.team.TeamListFragment
import com.setia.myfootballmatch.fragment.teamfavorite.TeamFavoriteFragment
import com.setia.myfootballmatch.model.Event
import com.setia.myfootballmatch.model.Team
import com.setia.myfootballmatch.teamdetail.TeamDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MatchListFragment.OnListFragmentInteractionListener,
        EventFavoriteFragment.EventFavoriteInteractionListener, TeamListFragment.TeamInteractionListener,
        TeamFavoriteFragment.TeamFavoriteInteractionListener {

    var currentNav: Int = 0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                if (currentNav != item.itemId) {
                    loadFragment(EventContainerFragment.newInstance())
                    currentNav = item.itemId
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                if (currentNav != item.itemId) {
                    loadFragment(TeamListFragment.newInstance())
                    currentNav = item.itemId
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                if (currentNav != item.itemId) {
                    loadFragment(FavoriteContainerFragment.newInstance())
                    currentNav = item.itemId
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(EventContainerFragment.newInstance())

        currentNav = R.id.navigation_matches
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment).commit()
    }

    override fun onListFragmentInteraction(item: Event?) {
        if (item != null) {
            startActivity<EventDetailActivity>("event" to item)
        }
    }

    override fun onTapEventFavorite(item: Event?) {
        if (item != null) {
            startActivity<EventDetailActivity>("event" to item)
        }
    }

    override fun onTapTeam(item: Team?) {
        if (item != null) {
            startActivity<TeamDetailActivity>("team" to item)
        }
    }

    override fun onTapTeamFavorite(item: Team?) {
        if (item != null) {
            startActivity<TeamDetailActivity>("team" to item)
        }
    }
}
