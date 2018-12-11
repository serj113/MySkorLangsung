package com.setia.myfootballmatch.eventdetail

import com.setia.myfootballmatch.helper.FootballClient
import com.setia.myfootballmatch.model.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventDetailPresenter(private val view: EventDetailView) {

    fun getEventDetail(teamId: String) {
        FootballClient.sharedInstance.get().getEventDetail(teamId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    val event = it.events?.get(0) ?: Event()
                    view.updateData(event)
                    view.updateView()
                    getTeamHomeLogo(event.idHomeTeam ?: "")
                    getTeamAwayLogo(event.idAwayTeam ?: "")
                }, {

                })
    }

    fun getTeamHomeLogo(idHomeTeam: String) {
        FootballClient.sharedInstance.get().getTeamDetail(idHomeTeam)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    it.teams?.get(0)?.strTeamLogo?.let { view.updateHomeLogo(it) }
                }, {

                })
    }

    fun getTeamAwayLogo(idAwayTeam: String) {
        FootballClient.sharedInstance.get().getTeamDetail(idAwayTeam)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    it.teams?.get(0)?.strTeamLogo?.let { view.updateAwayLogo(it) }
                }, {

                })
    }

}