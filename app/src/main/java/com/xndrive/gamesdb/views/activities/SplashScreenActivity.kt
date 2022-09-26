package com.xndrive.gamesdb.views.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.*
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.databinding.ActivitySplashScreenBinding
import com.xndrive.gamesdb.views.fragments.SplashFragment

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var splashBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //cara lama
        //val inflater = LayoutInflater.from(this)
        //splashScreenActivity = inflater.inflate(R.layout.activity_splash_screen, null)
        //setContentView(splashScreenActivity)

        //cara pakai view binding
        splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        determine()

    }

    private fun determine() {
//        setSupportActionBar(splashBinding.toolbar)

        val navControl = Navigation.findNavController(this, R.id.activity_splash_screen_fragmen)
//        val appBarConfiguration = AppBarConfiguration(navControl.graph)
//        splashBinding.toolbar.setupWithNavController(navControl, appBarConfiguration)
//        NavigationUI.setupActionBarWithNavController(this, navControl)
        NavigationUI.setupActionBarWithNavController(this, navControl)
    }
}