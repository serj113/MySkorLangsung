package com.setia.myfootballmatch.searchevent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import com.setia.myfootballmatch.R
import com.setia.myfootballmatch.eventdetail.EventDetailActivity
import com.setia.myfootballmatch.helper.FootballClient
import com.setia.myfootballmatch.model.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search_event.*
import org.jetbrains.anko.startActivity

class SearchEventActivity : AppCompatActivity(), SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener,
SearchEventListener {

    lateinit  var myAdapter: SearchEventRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_event)

        myAdapter = SearchEventRecyclerViewAdapter(this)
        match_rv.layoutManager = android.support.v7.widget.LinearLayoutManager(this)
        match_rv.adapter = myAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_activity_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setQueryHint("Search")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_search ->
                // Not implemented here
                return false
            else -> {
            }
        }
//        searchView.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0 == null || p0.trim().isEmpty()) {
            resetSearch()
            return false
        }


        FootballClient.sharedInstance.get().searchEvents(p0)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    var events = it.searchedEvents?.toMutableList() ?: mutableListOf()
                    myAdapter.mValues = events.filter { it.strSport == "Soccer" }.toMutableList()
                    myAdapter.notifyDataSetChanged()
                }, {
                })

        return false
    }

    private fun resetSearch() {
        myAdapter.mValues.clear()
        myAdapter.notifyDataSetChanged()
    }

    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
        return true
    }

    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        return true
    }

    override fun onTapSearchEvent(item: Event?) {
        if (item != null) {
            startActivity<EventDetailActivity>("event" to item)
        }
    }
}

interface SearchEventListener {
    fun onTapSearchEvent(item: Event?)
}
