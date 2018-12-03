package com.setia.myfootballmatch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LeagueResponse(
        @SerializedName("countrys")
        @Expose
        var leagues: List<League>? = null
)