package com.setia.myfootballmatch

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.setia.myfootballmatch.eventdetail.EventDetailActivity
import com.setia.myfootballmatch.fragment.event.MatchListFragment
import com.setia.myfootballmatch.fragment.event.Schedule
import com.setia.myfootballmatch.model.Event
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MatchListFragment.OnListFragmentInteractionListener {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_prev -> {
                loadFragment(MatchListFragment.newInstance(Schedule.PAST))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next -> {
                loadFragment(MatchListFragment.newInstance(Schedule.NEXT))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                loadFragment(MatchListFragment.newInstance(Schedule.FAVORITE))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(MatchListFragment.newInstance(Schedule.PAST))

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
}
