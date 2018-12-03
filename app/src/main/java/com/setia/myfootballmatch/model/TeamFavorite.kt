package com.setia.myfootballmatch.model

data class TeamFavorite(
        val id: Long?,
        val teamId: String?,
        val teamName: String?) {

    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
    }
}