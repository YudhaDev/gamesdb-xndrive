package com.xndrive.gamesdb.applications

import android.app.Application
import com.xndrive.gamesdb.models.room_databases.GamedbDatabase
import com.xndrive.gamesdb.models.room_repositories.GamesdbRepository

class GamedbApplication: Application() {
    private val database by lazy {
        GamedbDatabase.getGamedbDatabase(this@GamedbApplication)
    }
    val repository by lazy {
        GamesdbRepository(database.userBiodataDao(), database.userFavGameDao())
    }
}