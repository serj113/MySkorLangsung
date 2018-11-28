package com.setia.myfootballmatch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Player(
        @SerializedName("idPlayer")
        @Expose
        private val idPlayer: String? = null,
        @SerializedName("idTeam")
        @Expose
        private val idTeam: String? = null,
        @SerializedName("idSoccerXML")
        @Expose
        private val idSoccerXML: String? = null,
        @SerializedName("idPlayerManager")
        @Expose
        private val idPlayerManager: Any? = null,
        @SerializedName("strNationality")
        @Expose
        private val strNationality: String? = null,
        @SerializedName("strPlayer")
        @Expose
        private val strPlayer: String? = null,
        @SerializedName("strTeam")
        @Expose
        private val strTeam: String? = null,
        @SerializedName("strSport")
        @Expose
        private val strSport: String? = null,
        @SerializedName("intSoccerXMLTeamID")
        @Expose
        private val intSoccerXMLTeamID: Any? = null,
        @SerializedName("dateBorn")
        @Expose
        private val dateBorn: String? = null,
        @SerializedName("dateSigned")
        @Expose
        private val dateSigned: Any? = null,
        @SerializedName("strSigning")
        @Expose
        private val strSigning: String? = null,
        @SerializedName("strWage")
        @Expose
        private val strWage: String? = null,
        @SerializedName("strBirthLocation")
        @Expose
        private val strBirthLocation: String? = null,
        @SerializedName("strDescriptionEN")
        @Expose
        private val strDescriptionEN: String? = null,
        @SerializedName("strDescriptionDE")
        @Expose
        private val strDescriptionDE: Any? = null,
        @SerializedName("strDescriptionFR")
        @Expose
        private val strDescriptionFR: Any? = null,
        @SerializedName("strDescriptionCN")
        @Expose
        private val strDescriptionCN: Any? = null,
        @SerializedName("strDescriptionIT")
        @Expose
        private val strDescriptionIT: Any? = null,
        @SerializedName("strDescriptionJP")
        @Expose
        private val strDescriptionJP: Any? = null,
        @SerializedName("strDescriptionRU")
        @Expose
        private val strDescriptionRU: Any? = null,
        @SerializedName("strDescriptionES")
        @Expose
        private val strDescriptionES: Any? = null,
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
        @SerializedName("strDescriptionIL")
        @Expose
        private val strDescriptionIL: Any? = null,
        @SerializedName("strDescriptionPL")
        @Expose
        private val strDescriptionPL: Any? = null,
        @SerializedName("strGender")
        @Expose
        private val strGender: String? = null,
        @SerializedName("strPosition")
        @Expose
        private val strPosition: String? = null,
        @SerializedName("strCollege")
        @Expose
        private val strCollege: Any? = null,
        @SerializedName("strFacebook")
        @Expose
        private val strFacebook: String? = null,
        @SerializedName("strWebsite")
        @Expose
        private val strWebsite: String? = null,
        @SerializedName("strTwitter")
        @Expose
        private val strTwitter: String? = null,
        @SerializedName("strInstagram")
        @Expose
        private val strInstagram: String? = null,
        @SerializedName("strYoutube")
        @Expose
        private val strYoutube: String? = null,
        @SerializedName("strHeight")
        @Expose
        private val strHeight: String? = null,
        @SerializedName("strWeight")
        @Expose
        private val strWeight: String? = null,
        @SerializedName("intLoved")
        @Expose
        private val intLoved: String? = null,
        @SerializedName("strThumb")
        @Expose
        private val strThumb: String? = null,
        @SerializedName("strCutout")
        @Expose
        private val strCutout: Any? = null,
        @SerializedName("strBanner")
        @Expose
        private val strBanner: Any? = null,
        @SerializedName("strFanart1")
        @Expose
        private val strFanart1: Any? = null,
        @SerializedName("strFanart2")
        @Expose
        private val strFanart2: Any? = null,
        @SerializedName("strFanart3")
        @Expose
        private val strFanart3: Any? = null,
        @SerializedName("strFanart4")
        @Expose
        private val strFanart4: Any? = null,
        @SerializedName("strLocked")
        @Expose
        private val strLocked: String? = null
)