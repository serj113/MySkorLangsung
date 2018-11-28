package com.setia.myfootballmatch.helper

import com.setia.myfootballmatch.model.EventResponse
import com.setia.myfootballmatch.model.PlayerResponse
import com.setia.myfootballmatch.model.TeamResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballService {

    @GET("api/v1/json/1/eventsnextleague.php?id=4328")
    fun getNextSchedule(@Query("id") leagueId: String): Observable<EventResponse>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getPastSchedule(@Query("id") leagueId: String): Observable<EventResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamDetail(@Query("id") teamId: String): Observable<TeamResponse>

    @GET("api/v1/json/1/lookupevent.php")
    fun getEventDetail(@Query("id") eventId: String): Observable<EventResponse>

    @GET("api/v1/json/1/lookupplayer.php")
    fun getTeamPlayers(@Query("id") teamId: String): Observable<PlayerResponse>

}