package com.xndrive.gamesdb.etc

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

class MyGlideManager(val context : Context, val url : String, val imageView: ImageView) {
    fun generateImage() {
        val circularProgress = CircularProgressDrawable(context)
        circularProgress.strokeWidth = 5f
        circularProgress.centerRadius = 30f
        circularProgress.start()

        Glide.with(context)
            .load(url)
            .placeholder(circularProgress)
            .into(imageView)
    }

}