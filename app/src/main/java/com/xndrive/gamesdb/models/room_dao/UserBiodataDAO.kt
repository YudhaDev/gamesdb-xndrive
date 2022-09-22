package com.xndrive.gamesdb.models.room_dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.xndrive.gamesdb.models.room_entities.UserBiodataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserBiodataDAO {
    @Insert
    suspend fun insertUserBiodata(userBiodataEntity: UserBiodataEntity)
//    @Update
//    suspend fun updateUserBiodata()
    @Query("SELECT * FROM user_biodata WHERE user_id = :user_id")
    fun getUserBiodata(user_id: Int) : Flow<List<UserBiodataEntity>>
    @Query("SELECT * FROM user_biodata")
    fun getAllUser() : Flow<List<UserBiodataEntity>>

}