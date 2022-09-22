package com.xndrive.gamesdb.applications

import android.app.Application
import android.content.Context
import com.xndrive.gamesdb.models.room_databases.GamedbDatabase
import com.xndrive.gamesdb.models.room_repositories.GamesdbRepository

class GamedbApplication(applicationCon: Context) {
    private val database by lazy {
        GamedbDatabase.getGamedbDatabase(applicationCon)
    }
    val repository by lazy {
        GamesdbRepository(database.userBiodataDao(), database.userFavGameDao())
    }
}