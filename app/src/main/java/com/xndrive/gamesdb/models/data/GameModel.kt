package com.xndrive.gamesdb.models.data

import java.io.Serializable

data class GameModel(
    val title: String,
    val developer: String,
    val description: String?,
    val picture: String,
    val picture2: String,
    val genres: String?
) : Serializable
