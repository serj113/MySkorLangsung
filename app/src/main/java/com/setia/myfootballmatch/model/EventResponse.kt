package com.setia.myfootballmatch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EventResponse(
        @SerializedName("events")
        @Expose
        var events: List<Event>? = null,
        @SerializedName("event")
        @Expose
        var searchedEvents: List<Event>? = null
)