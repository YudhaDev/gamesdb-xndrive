package com.xndrive.gamesdb.models.room_entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_favgames")
data class FavGamesEntity(
    @PrimaryKey val game_id: String,
    @ColumnInfo val game_name: String,
    @ColumnInfo val game_description: String
)
