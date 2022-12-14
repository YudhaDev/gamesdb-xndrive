package com.xndrive.gamesdb.models.data

import java.io.Serializable

data class UserBiodataModel(
    var user_photo_profile_path : String,
    var user_name : String,
    var user_email : String,
    var user_id : Int
) : Serializable {
}