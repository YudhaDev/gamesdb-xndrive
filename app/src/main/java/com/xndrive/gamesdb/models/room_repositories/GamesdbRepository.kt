package com.xndrive.gamesdb.models.room_repositories

import androidx.annotation.WorkerThread
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
        userDao.insertUserBiodata(userBiodataEntity)
    }

    fun getUserBiodata(user_id : Int) : Flow<List<UserBiodataEntity>>{
        return userDao.getUserBiodata(user_id)
    }

    suspend fun updateUserBiodata(){}

    suspend fun insertFavGame(){}
}