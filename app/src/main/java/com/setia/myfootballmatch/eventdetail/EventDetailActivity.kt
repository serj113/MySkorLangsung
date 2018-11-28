package com.setia.myfootballmatch.eventdetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.setia.myfootballmatch.R
import com.setia.myfootballmatch.helper.database
import com.setia.myfootballmatch.model.Event
import com.setia.myfootballmatch.model.Favorite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.delete

class EventDetailActivity : AppCompatActivity(), EventDetailView {

    private lateinit var presenter: EventDetailPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event = intent.extras.get("event") as Event

        presenter = EventDetailPresenter(this)

        favoriteState()

        setContentView(R.layout.activity_event_detail)

        presenter.getEventDetail(event.idEvent ?: "")

    }

    override fun updateData(data: Event) {
        event = data
    }

    override fun updateView() {
        date_tv.text = event.dateEvent ?: ""

        team_1_score_tv.text = "${event.intHomeScore ?: 0}"
        team_1_name_tv.text = event.strHomeTeam ?: ""
        team_1_goals_tv.text = "${event.strHomeGoalDetails ?: ""}"
        team_1_shots_tv.text = "${event.intHomeShots ?: 0}"
        team_1_goal_keeper_tv.text = "${event.strHomeLineupGoalkeeper ?: ""}"
        team_1_defense_tv.text = "${event.strHomeLineupDefense ?: ""}"
        team_1_midfield_tv.text = "${event.strHomeLineupMidfield ?: ""}"
        team_1_forward_tv.text = "${event.strHomeLineupForward ?: ""}"
        team_1_subtitute_tv.text = "${event.strHomeLineupSubstitutes ?: ""}"

        team_2_score_tv.text = "${event.intAwayScore ?: 0}"
        team_2_name_tv.text = event.strAwayTeam ?: ""
        team_2_goals_tv.text = "${event.strAwayGoalDetails ?: ""}"
        team_2_shots_tv.text = "${event.intAwayShots ?: 0}"
        team_2_goal_keeper_tv.text = "${event.strAwayLineupGoalkeeper ?: ""}"
        team_2_defense_tv.text = "${event.strAwayLineupDefense ?: ""}"
        team_2_midfield_tv.text = "${event.strAwayLineupMidfield ?: ""}"
        team_2_forward_tv.text = "${event.strAwayLineupForward ?: ""}"
        team_2_subtitute_tv.text = "${event.strAwayLineupSubstitutes ?: ""}"
    }

    override fun updateHomeLogo(logo: String) {
        Picasso.get().load(logo).into(team_1_logo_iv)
    }

    override fun updateAwayLogo(logo: String) {
        Picasso.get().load(logo).into(team_2_logo_iv)
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
                insert(Favorite.TABLE_EVENT,
                        Favorite.EVENT_ID to event.idEvent,
                        Favorite.TEAM_HOME_ID to event.idHomeTeam,
                        Favorite.TEAM_HOME_NAME to event.strHomeTeam,
                        Favorite.TEAM_HOME_SCORE to event.intHomeScore,
                        Favorite.TEAM_AWAY_ID to event.idAwayTeam,
                        Favorite.TEAM_AWAY_NAME to event.strAwayTeam,
                        Favorite.TEAM_AWAY_SCORE to event.intAwayScore)
            }
            Snackbar.make(scrollView, "Added to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(scrollView, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_EVENT, "(EVENT_ID = {id})",
                        "id" to (event.idEvent ?: ""))
            }
            Snackbar.make(scrollView, "Removed to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(scrollView, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
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
            val result = select(Favorite.TABLE_EVENT)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to (event.idEvent ?: ""))
            val event = result.parseList(classParser<Favorite>())
            if (!event.isEmpty()) isFavorite = true
        }
    }

}
