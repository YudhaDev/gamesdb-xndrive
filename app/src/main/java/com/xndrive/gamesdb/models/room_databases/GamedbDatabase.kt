package com.xndrive.gamesdb.models.room_databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xndrive.gamesdb.models.room_dao.UserBiodataDAO
import com.xndrive.gamesdb.models.room_dao.UserFavGameDAO
import com.xndrive.gamesdb.models.room_entities.FavGamesEntity
import com.xndrive.gamesdb.models.room_entities.UserBiodataEntity

@Database(entities = [UserBiodataEntity::class, FavGamesEntity::class], version = 1)
abstract class GamedbDatabase : RoomDatabase(){
    abstract fun userBiodataDao(): UserBiodataDAO
    abstract fun userFavGameDao(): UserFavGameDAO

    companion object{
        @Volatile
        private var INSTANCE : GamedbDatabase? = null

        fun getGamedbDatabase(context: Context) : GamedbDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, GamedbDatabase::class.java, "gamedb_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }

        }
    }


}