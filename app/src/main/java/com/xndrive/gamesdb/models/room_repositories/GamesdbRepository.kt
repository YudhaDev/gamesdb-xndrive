package com.xndrive.gamesdb.models.room_repositories

import android.util.Log
import androidx.annotation.WorkerThread
import com.xndrive.gamesdb.models.data.UserBiodataModel
import com.xndrive.gamesdb.models.room_dao.UserBiodataDAO
import com.xndrive.gamesdb.models.room_dao.UserFavGameDAO
import com.xndrive.gamesdb.models.room_entities.UserBiodataEntity
import kotlinx.coroutines.flow.Flow

class GamesdbRepository(
    private val userDao : UserBiodataDAO,
    private val userFavGameDAO: UserFavGameDAO
) {
    @WorkerThread
    suspend fun insertUserBiodata(userBiodataEntity: UserBiodataEntity){
        Log.d("gamesdb", "memasukan data: ${userBiodataEntity.toString()}")
        userDao.insertUserBiodata(userBiodataEntity)
    }

    fun getUserBiodata(user_id : Int) : Flow<List<UserBiodataEntity>>{
        return userDao.getUserBiodata(user_id)
    }

    fun getAllUser() = userDao.getAllUser()

    @WorkerThread
    suspend fun updateUserBiodata(userBiodataEntity: UserBiodataEntity){
        userDao.updateUserBiodate(userBiodataEntity)
    }

    suspend fun insertFavGame(){}

}