package com.setia.myfootballmatch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Event(
        @SerializedName("idEvent")
        @Expose
        var idEvent: String? = null,
        @SerializedName("idSoccerXML")
        @Expose
        var idSoccerXML: String? = null,
        @SerializedName("strEvent")
        @Expose
        var strEvent: String? = null,
        @SerializedName("strFilename")
        @Expose
        var strFilename: String? = null,
        @SerializedName("strSport")
        @Expose
        var strSport: String? = null,
        @SerializedName("idLeague")
        @Expose
        var idLeague: String? = null,
        @SerializedName("strLeague")
        @Expose
        var strLeague: String? = null,
        @SerializedName("strSeason")
        @Expose
        var strSeason: String? = null,
        @SerializedName("strDescriptionEN")
        @Expose
        var strDescriptionEN: Any? = null,
        @SerializedName("strHomeTeam")
        @Expose
        var strHomeTeam: String? = null,
        @SerializedName("strAwayTeam")
        @Expose
        var strAwayTeam: String? = null,
        @SerializedName("intHomeScore")
        @Expose
        var intHomeScore: Any? = null,
        @SerializedName("intRound")
        @Expose
        var intRound: String? = null,
        @SerializedName("intAwayScore")
        @Expose
        var intAwayScore: Any? = null,
        @SerializedName("intSpectators")
        @Expose
        var intSpectators: Any? = null,
        @SerializedName("strHomeGoalDetails")
        @Expose
        var strHomeGoalDetails: Any? = null,
        @SerializedName("strHomeRedCards")
        @Expose
        var strHomeRedCards: Any? = null,
        @SerializedName("strHomeYellowCards")
        @Expose
        var strHomeYellowCards: Any? = null,
        @SerializedName("strHomeLineupGoalkeeper")
        @Expose
        var strHomeLineupGoalkeeper: Any? = null,
        @SerializedName("strHomeLineupDefense")
        @Expose
        var strHomeLineupDefense: Any? = null,
        @SerializedName("strHomeLineupMidfield")
        @Expose
        var strHomeLineupMidfield: Any? = null,
        @SerializedName("strHomeLineupForward")
        @Expose
        var strHomeLineupForward: Any? = null,
        @SerializedName("strHomeLineupSubstitutes")
        @Expose
        var strHomeLineupSubstitutes: Any? = null,
        @SerializedName("strHomeFormation")
        @Expose
        var strHomeFormation: Any? = null,
        @SerializedName("strAwayRedCards")
        @Expose
        var strAwayRedCards: Any? = null,
        @SerializedName("strAwayYellowCards")
        @Expose
        var strAwayYellowCards: Any? = null,
        @SerializedName("strAwayGoalDetails")
        @Expose
        var strAwayGoalDetails: Any? = null,
        @SerializedName("strAwayLineupGoalkeeper")
        @Expose
        var strAwayLineupGoalkeeper: Any? = null,
        @SerializedName("strAwayLineupDefense")
        @Expose
        var strAwayLineupDefense: Any? = null,
        @SerializedName("strAwayLineupMidfield")
        @Expose
        var strAwayLineupMidfield: Any? = null,
        @SerializedName("strAwayLineupForward")
        @Expose
        var strAwayLineupForward: Any? = null,
        @SerializedName("strAwayLineupSubstitutes")
        @Expose
        var strAwayLineupSubstitutes: Any? = null,
        @SerializedName("strAwayFormation")
        @Expose
        var strAwayFormation: Any? = null,
        @SerializedName("intHomeShots")
        @Expose
        var intHomeShots: Any? = null,
        @SerializedName("intAwayShots")
        @Expose
        var intAwayShots: Any? = null,
        @SerializedName("dateEvent")
        @Expose
        var dateEvent: String? = null,
        @SerializedName("strDate")
        @Expose
        var strDate: String? = null,
        @SerializedName("strTime")
        @Expose
        var strTime: String? = null,
        @SerializedName("strTVStation")
        @Expose
        var strTVStation: Any? = null,
        @SerializedName("idHomeTeam")
        @Expose
        var idHomeTeam: String? = null,
        @SerializedName("idAwayTeam")
        @Expose
        var idAwayTeam: String? = null,
        @SerializedName("strResult")
        @Expose
        var strResult: Any? = null,
        @SerializedName("strCircuit")
        @Expose
        var strCircuit: Any? = null,
        @SerializedName("strCountry")
        @Expose
        var strCountry: Any? = null,
        @SerializedName("strCity")
        @Expose
        var strCity: Any? = null,
        @SerializedName("strPoster")
        @Expose
        var strPoster: Any? = null,
        @SerializedName("strFanart")
        @Expose
        var strFanart: Any? = null,
        @SerializedName("strThumb")
        @Expose
        var strThumb: Any? = null,
        @SerializedName("strBanner")
        @Expose
        var strBanner: Any? = null,
        @SerializedName("strMap")
        @Expose
        var strMap: Any? = null,
        @SerializedName("strLocked")
        @Expose
        var strLocked: String? = null
): Serializable