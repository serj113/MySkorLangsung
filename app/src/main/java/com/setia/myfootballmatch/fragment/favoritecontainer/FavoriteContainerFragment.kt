package com.setia.myfootballmatch.fragment.favoritecontainer

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.setia.myfootballmatch.R
import org.jetbrains.anko.find

class FavoriteContainerFragment : Fragment() {
    private var mPagerAdapter: FavoriteContainerPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_container, container, false)
        if (mPagerAdapter == null) {
            mPagerAdapter = FavoriteContainerPagerAdapter(childFragmentManager)
        }

        val container = view.find<ViewPager>(R.id.favorite_container_view_pager)
        val tabs = view.find<TabLayout>(R.id.favorite_container_tabs)

        container.adapter = mPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
        tabs.setupWithViewPager(container)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                FavoriteContainerFragment()
    }
}
