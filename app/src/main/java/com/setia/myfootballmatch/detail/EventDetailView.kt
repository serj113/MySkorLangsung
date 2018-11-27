package com.setia.myfootballmatch.detail

import com.setia.myfootballmatch.model.Event

interface EventDetailView {

    fun updateData(data: Event)
    fun updateView()
    fun updateHomeLogo(logo: String)
    fun updateAwayLogo(logo: String)

}