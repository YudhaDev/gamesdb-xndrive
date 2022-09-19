package com.xndrive.gamesdb.models.room_entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_biodata")
data class UserBiodataEntity(
    @ColumnInfo val user_profile_image : String,
    @ColumnInfo val user_name: String,
    @ColumnInfo val user_email: String,
    @PrimaryKey(autoGenerate = true) val user_id : Int = 0

    )
