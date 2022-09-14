package com.xndrive.gamesdb.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.*
import android.view.View
import com.xndrive.gamesdb.databinding.ActivitySplashScreenBinding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : AppCompatActivity() {
    var splashScreenActivity : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //cara lama
        //val inflater = LayoutInflater.from(this)
        //splashScreenActivity = inflater.inflate(R.layout.activity_splash_screen, null)
        //setContentView(splashScreenActivity)

        //cara pakai view binding
        val splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        splashBinding.splashScreenLoadingTextview.setText("loading...")
            Handler().postDelayed({
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            }, 3000)

    }
}