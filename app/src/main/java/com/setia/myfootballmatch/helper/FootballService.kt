package com.setia.myfootballmatch.helper

import com.setia.myfootballmatch.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballService {

    @GET("api/v1/json/1/search_all_leagues.php?s=Soccer")
    fun getAllLeague(): Observable<LeagueResponse>

    @GET("api/v1/json/1/eventsnextleague.php?id=4328")
    fun getNextSchedule(@Query("id") leagueId: String): Observable<EventResponse>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getPastSchedule(@Query("id") leagueId: String): Observable<EventResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamDetail(@Query("id") teamId: String): Observable<TeamResponse>

    @GET("api/v1/json/1/lookup_all_teams.php")
    fun getAllTeam(@Query("id") league: String): Observable<TeamResponse>

    @GET("api/v1/json/1/searchteams.php")
    fun searchTeams(@Query("t") team: String): Single<TeamResponse>

    @GET("api/v1/json/1/search_all_teams.php")
    fun getListTeam(@Query("l") league: String): Observable<TeamResponse>

    @GET("api/v1/json/1/lookupevent.php")
    fun getEventDetail(@Query("id") eventId: String): Observable<EventResponse>

    @GET("api/v1/json/1/searchevents.php")
    fun searchEvents(@Query("e") eventName: String): Single<EventResponse>

    @GET("api/v1/json/1/lookupplayer.php")
    fun getTeamPlayers(@Query("id") teamId: String): Observable<PlayerResponse>

    @GET("api/v1/json/1/lookup_all_players.php")
    fun getAllTeamPlayer(@Query("id") teamId: String): Observable<PlayerResponse>

}