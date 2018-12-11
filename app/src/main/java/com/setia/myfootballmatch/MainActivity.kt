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
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.content.Intent
import com.setia.myfootballmatch.searchevent.SearchEventActivity
import java.util.*


class MainActivity : AppCompatActivity(), MatchListFragment.OnListFragmentInteractionListener,
        EventFavoriteFragment.EventFavoriteInteractionListener, TeamListFragment.TeamInteractionListener,
        TeamFavoriteFragment.TeamFavoriteInteractionListener {

    var currentNav: Int = 0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                if (currentNav != item.itemId) {
                    val fragment = EventContainerFragment.newInstance()
                    fragment.setHasOptionsMenu(true)
                    loadFragment(fragment)
                    currentNav = item.itemId
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                if (currentNav != item.itemId) {
                    val fragment = TeamListFragment.newInstance()
                    fragment.setHasOptionsMenu(true)
                    loadFragment(fragment)
                    currentNav = item.itemId
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                if (currentNav != item.itemId) {
                    val fragment = FavoriteContainerFragment.newInstance()
                    fragment.setHasOptionsMenu(false)
                    loadFragment(fragment)
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

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_matches
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

    override fun openSearchActivity() {
        startActivity<SearchEventActivity>()
    }

    override fun addToCalendar(item: Event?, date: Date?) {
        if (item != null && date != null) {
            val calIntent = Intent(Intent.ACTION_INSERT)
            calIntent.type = "vnd.android.cursor.item/event"
            calIntent.putExtra(Events.TITLE, item.strEvent)
            calIntent.putExtra(Events.DESCRIPTION, item.strEvent)

            calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                    date.time)

            startActivity(calIntent)
        }
    }
}
