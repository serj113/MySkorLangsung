package com.setia.myfootballmatch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class League(
        @SerializedName("idLeague")
        @Expose
        val idLeague: String? = null,
        @SerializedName("idSoccerXML")
        @Expose
        val idSoccerXML: Any? = null,
        @SerializedName("strSport")
        @Expose
        val strSport: String? = null,
        @SerializedName("strLeague")
        @Expose
        val strLeague: String? = null,
        @SerializedName("strLeagueAlternate")
        @Expose
        val strLeagueAlternate: String? = null,
        @SerializedName("idCup")
        @Expose
        val idCup: String? = null,
        @SerializedName("intFormedYear")
        @Expose
        val intFormedYear: String? = null,
        @SerializedName("dateFirstEvent")
        @Expose
        val dateFirstEvent: String? = null,
        @SerializedName("strGender")
        @Expose
        val strGender: String? = null,
        @SerializedName("strCountry")
        @Expose
        val strCountry: String? = null,
        @SerializedName("strWebsite")
        @Expose
        val strWebsite: String? = null,
        @SerializedName("strFacebook")
        @Expose
        val strFacebook: String? = null,
        @SerializedName("strTwitter")
        @Expose
        val strTwitter: String? = null,
        @SerializedName("strYoutube")
        @Expose
        val strYoutube: String? = null,
        @SerializedName("strRSS")
        @Expose
        val strRSS: String? = null,
        @SerializedName("strDescriptionEN")
        @Expose
        val strDescriptionEN: String? = null,
        @SerializedName("strDescriptionDE")
        @Expose
        val strDescriptionDE: Any? = null,
        @SerializedName("strDescriptionFR")
        @Expose
        val strDescriptionFR: String? = null,
        @SerializedName("strDescriptionIT")
        @Expose
        val strDescriptionIT: Any? = null,
        @SerializedName("strDescriptionCN")
        @Expose
        private val strDescriptionCN: Any? = null,
        @SerializedName("strDescriptionJP")
        @Expose
        private val strDescriptionJP: Any? = null,
        @SerializedName("strDescriptionRU")
        @Expose
        private val strDescriptionRU: Any? = null,
        @SerializedName("strDescriptionES")
        @Expose
        private val strDescriptionES: String? = null,
        @SerializedName("strDescriptionPT")
        @Expose
        private val strDescriptionPT: Any? = null,
        @SerializedName("strDescriptionSE")
        @Expose
        private val strDescriptionSE: Any? = null,
        @SerializedName("strDescriptionNL")
        @Expose
        private val strDescriptionNL: Any? = null,
        @SerializedName("strDescriptionHU")
        @Expose
        private val strDescriptionHU: Any? = null,
        @SerializedName("strDescriptionNO")
        @Expose
        private val strDescriptionNO: Any? = null,
        @SerializedName("strDescriptionPL")
        @Expose
        private val strDescriptionPL: Any? = null,
        @SerializedName("strFanart1")
        @Expose
        private val strFanart1: String? = null,
        @SerializedName("strFanart2")
        @Expose
        private val strFanart2: Any? = null,
        @SerializedName("strFanart3")
        @Expose
        private val strFanart3: Any? = null,
        @SerializedName("strFanart4")
        @Expose
        private val strFanart4: Any? = null,
        @SerializedName("strBanner")
        @Expose
        private val strBanner: Any? = null,
        @SerializedName("strBadge")
        @Expose
        private val strBadge: String? = null,
        @SerializedName("strLogo")
        @Expose
        private val strLogo: String? = null,
        @SerializedName("strPoster")
        @Expose
        val strPoster: Any? = null,
        @SerializedName("strTrophy")
        @Expose
        val strTrophy: String? = null,
        @SerializedName("strNaming")
        @Expose
        val strNaming: String? = null,
        @SerializedName("strLocked")
        @Expose
        val strLocked: String? = null
)