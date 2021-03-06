package com.setia.myfootballmatch.fragment.overview

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.setia.myfootballmatch.R
import org.jetbrains.anko.find

private const val ARG_OVERVIEW = "overview"

class OverviewFragment : Fragment() {
    private var overview: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            overview = it.getString(ARG_OVERVIEW)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        view.find<TextView>(R.id.overview_tv).text = overview

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(overview: String) =
                OverviewFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_OVERVIEW, overview)
                    }
                }
    }
}
