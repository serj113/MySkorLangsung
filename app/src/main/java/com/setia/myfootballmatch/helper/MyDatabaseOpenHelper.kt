package com.setia.myfootballmatch.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.setia.myfootballmatch.model.Favorite
import com.setia.myfootballmatch.model.TeamFavorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Favorite.TABLE_EVENT, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.EVENT_ID to TEXT,
                Favorite.TEAM_HOME_ID to TEXT,
                Favorite.TEAM_HOME_NAME to TEXT,
                Favorite.TEAM_HOME_SCORE to TEXT,
                Favorite.TEAM_AWAY_ID to TEXT,
                Favorite.TEAM_AWAY_NAME to TEXT,
                Favorite.TEAM_AWAY_SCORE to TEXT)

        db.createTable(TeamFavorite.TABLE_TEAM, true,
                TeamFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamFavorite.TEAM_ID to TEXT,
                TeamFavorite.TEAM_NAME to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_EVENT, true)
        db.dropTable(TeamFavorite.TABLE_TEAM, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)