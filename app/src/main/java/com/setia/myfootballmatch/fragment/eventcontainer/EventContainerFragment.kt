package com.setia.myfootballmatch.fragment.eventcontainer

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*

import com.setia.myfootballmatch.R
import org.jetbrains.anko.find


class EventContainerFragment : Fragment() {

    private var mPagerAdapter: EventContainerPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_event_container, container, false)
        if (mPagerAdapter == null) {
            mPagerAdapter = EventContainerPagerAdapter(childFragmentManager)
        }

        val container = view.find<ViewPager>(R.id.event_container_view_pager)
        val tabs = view.find<TabLayout>(R.id.tabs)

        container.adapter = mPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
        tabs.setupWithViewPager(container)
        return view
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                EventContainerFragment()
    }
}
