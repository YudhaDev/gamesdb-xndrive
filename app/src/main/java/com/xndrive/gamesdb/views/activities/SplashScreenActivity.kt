package com.xndrive.gamesdb.views.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.databinding.ActivitySplashScreenBinding
import com.xndrive.gamesdb.databinding.FragmentSplashBinding
import com.xndrive.gamesdb.views.fragments.SplashFragment
import java.util.logging.Logger

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var splashBinding: Any

    companion object {
        var iteration = 0
        var navControl : NavController? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cara lama
        //val inflater = LayoutInflater.from(this)
        //splashScreenActivity = inflater.inflate(R.layout.activity_splash_screen, null)
        //setContentView(splashScreenActivity)

        //cara pakai view binding
//        splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)

        if (iteration==0){
            Log.d("ayolahmamen", "iteration: $iteration")
            splashBinding = FragmentSplashBinding.inflate(layoutInflater)
            val layout = layoutInflater.inflate(R.layout.test_layout, null)
            setContentView((splashBinding as FragmentSplashBinding).root)
            iteration++
            Handler(Looper.getMainLooper()).postDelayed({AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)}, 1500)
//            recreate()
        }
        else {
            splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
            setContentView((splashBinding as ActivitySplashScreenBinding).root)
            determine()
            Log.d("ayolahmamen", "iteration: $iteration")
        }

//        setContentView(splashBinding.root)




    }

    private fun determine() {
//        setSupportActionBar(splashBinding.toolbar)

//        val appBarConfiguration = AppBarConfiguration(navControl.graph)
//        splashBinding.toolbar.setupWithNavController(navControl, appBarConfiguration)
//        NavigationUI.setupActionBarWithNavController(this, navControl)

        navControl = Navigation.findNavController(this, R.id.activity_splash_screen_fragment)
        NavigationUI.setupActionBarWithNavController(this, navControl!!)
        //ganti theme
        supportActionBar?.hide()
    }

    override fun recreate() {
        Log.d("ayolahmamen", "masuk recreate")
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        Log.d("ayolahmamen", "keluar recreate")
    }
}