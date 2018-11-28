package com.setia.myfootballmatch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PlayerResponse(
        @SerializedName("player")
        @Expose
        var players: List<Player>? = null
)