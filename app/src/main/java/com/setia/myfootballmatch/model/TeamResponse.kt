package com.setia.myfootballmatch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TeamResponse(
        @SerializedName("teams")
        @Expose
        var teams: List<Team>? = null
)