package com.setia.myfootballmatch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Player(
        @SerializedName("idPlayer")
        @Expose
        val idPlayer: String? = null,
        @SerializedName("idTeam")
        @Expose
        val idTeam: String? = null,
        @SerializedName("idSoccerXML")
        @Expose
        val idSoccerXML: String? = null,
        @SerializedName("idPlayerManager")
        @Expose
        val idPlayerManager: Any? = null,
        @SerializedName("strNationality")
        @Expose
        val strNationality: String? = null,
        @SerializedName("strPlayer")
        @Expose
        val strPlayer: String? = null,
        @SerializedName("strTeam")
        @Expose
        val strTeam: String? = null,
        @SerializedName("strSport")
        @Expose
        val strSport: String? = null,
        @SerializedName("intSoccerXMLTeamID")
        @Expose
        val intSoccerXMLTeamID: Any? = null,
        @SerializedName("dateBorn")
        @Expose
        val dateBorn: String? = null,
        @SerializedName("dateSigned")
        @Expose
        val dateSigned: Any? = null,
        @SerializedName("strSigning")
        @Expose
        val strSigning: String? = null,
        @SerializedName("strWage")
        @Expose
        val strWage: String? = null,
        @SerializedName("strBirthLocation")
        @Expose
        val strBirthLocation: String? = null,
        @SerializedName("strDescriptionEN")
        @Expose
        val strDescriptionEN: String? = null,
        @SerializedName("strDescriptionDE")
        @Expose
        val strDescriptionDE: Any? = null,
        @SerializedName("strDescriptionFR")
        @Expose
        val strDescriptionFR: Any? = null,
        @SerializedName("strDescriptionCN")
        @Expose
        val strDescriptionCN: Any? = null,
        @SerializedName("strDescriptionIT")
        @Expose
        val strDescriptionIT: Any? = null,
        @SerializedName("strDescriptionJP")
        @Expose
        val strDescriptionJP: Any? = null,
        @SerializedName("strDescriptionRU")
        @Expose
        val strDescriptionRU: Any? = null,
        @SerializedName("strDescriptionES")
        @Expose
        val strDescriptionES: Any? = null,
        @SerializedName("strDescriptionPT")
        @Expose
        val strDescriptionPT: Any? = null,
        @SerializedName("strDescriptionSE")
        @Expose
        val strDescriptionSE: Any? = null,
        @SerializedName("strDescriptionNL")
        @Expose
        val strDescriptionNL: Any? = null,
        @SerializedName("strDescriptionHU")
        @Expose
        val strDescriptionHU: Any? = null,
        @SerializedName("strDescriptionNO")
        @Expose
        val strDescriptionNO: Any? = null,
        @SerializedName("strDescriptionIL")
        @Expose
        val strDescriptionIL: Any? = null,
        @SerializedName("strDescriptionPL")
        @Expose
        val strDescriptionPL: Any? = null,
        @SerializedName("strGender")
        @Expose
        val strGender: String? = null,
        @SerializedName("strPosition")
        @Expose
        val strPosition: String? = null,
        @SerializedName("strCollege")
        @Expose
        val strCollege: Any? = null,
        @SerializedName("strFacebook")
        @Expose
        val strFacebook: String? = null,
        @SerializedName("strWebsite")
        @Expose
        val strWebsite: String? = null,
        @SerializedName("strTwitter")
        @Expose
        val strTwitter: String? = null,
        @SerializedName("strInstagram")
        @Expose
        val strInstagram: String? = null,
        @SerializedName("strYoutube")
        @Expose
        val strYoutube: String? = null,
        @SerializedName("strHeight")
        @Expose
        val strHeight: String? = null,
        @SerializedName("strWeight")
        @Expose
        val strWeight: String? = null,
        @SerializedName("intLoved")
        @Expose
        val intLoved: String? = null,
        @SerializedName("strThumb")
        @Expose
        val strThumb: String? = null,
        @SerializedName("strCutout")
        @Expose
        val strCutout: String? = null,
        @SerializedName("strBanner")
        @Expose
        val strBanner: String? = null,
        @SerializedName("strFanart1")
        @Expose
        val strFanart1: Any? = null,
        @SerializedName("strFanart2")
        @Expose
        val strFanart2: Any? = null,
        @SerializedName("strFanart3")
        @Expose
        val strFanart3: Any? = null,
        @SerializedName("strFanart4")
        @Expose
        val strFanart4: Any? = null,
        @SerializedName("strLocked")
        @Expose
        val strLocked: String? = null
): Serializable