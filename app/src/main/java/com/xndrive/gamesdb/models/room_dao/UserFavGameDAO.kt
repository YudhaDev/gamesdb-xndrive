package com.xndrive.gamesdb.models.room_dao

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserFavGameDAO {
    @Insert
    suspend fun insertUserFavGame(){}

}