package com.xndrive.gamesdb.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.databinding.ActivityRateGameBinding

class RateGameActivity : AppCompatActivity() {
    private lateinit var activityRateGameBinding: ActivityRateGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRateGameBinding = ActivityRateGameBinding.inflate(layoutInflater)
        setContentView(activityRateGameBinding.root)

        determine()
    }

    private fun determine() {
        val navControl = Navigation.findNavController(this, R.id.activity_rate_game_fragment)
        NavigationUI.setupActionBarWithNavController(this, navControl)


    }

}