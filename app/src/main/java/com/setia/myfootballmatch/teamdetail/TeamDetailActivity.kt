package com.setia.myfootballmatch.teamdetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.setia.myfootballmatch.R
import com.setia.myfootballmatch.helper.database
import com.setia.myfootballmatch.model.Team
import com.setia.myfootballmatch.model.TeamFavorite
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.delete
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter

    private lateinit var mPagerAdapter: TeamPagerAdapter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        team = intent.extras?.get("team") as Team

        presenter = TeamDetailPresenter(this)

        setContentView(R.layout.activity_team_detail)

        setSupportActionBar(htab_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Picasso.get().load(team.strTeamLogo).into(team_iv)

        mPagerAdapter = TeamPagerAdapter(supportFragmentManager, team)

        team_detail_vp.adapter = mPagerAdapter

        team_detail_vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(team_detail_tabs))
        team_detail_tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(team_detail_vp))
        team_detail_tabs.setupWithViewPager(team_detail_vp)

        favoriteState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_event_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(TeamFavorite.TABLE_TEAM,
                        TeamFavorite.TEAM_ID to team.idTeam,
                        TeamFavorite.TEAM_NAME to team.strTeam)
            }
            Snackbar.make(team_detail_vp, "Added to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(team_detail_vp, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(TeamFavorite.TABLE_TEAM, "(TEAM_ID = {id})",
                        "id" to (team.idTeam ?: ""))
            }
            Snackbar.make(team_detail_vp, "Removed to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(team_detail_vp, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_filled)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_outlined)
    }

    private fun favoriteState() {
        database.use {
            val result = select(TeamFavorite.TABLE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to (team.idTeam ?: ""))
            val event = result.parseList(classParser<TeamFavorite>())
            if (!event.isEmpty()) isFavorite = true
        }
    }
}
