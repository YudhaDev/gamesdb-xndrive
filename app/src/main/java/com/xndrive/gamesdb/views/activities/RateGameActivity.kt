package com.xndrive.gamesdb.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.databinding.ActivityRateGameBinding
import com.xndrive.gamesdb.models.data.GameModel
import com.xndrive.gamesdb.views.fragments.PickStarFragmentDirections

class RateGameActivity : AppCompatActivity() {
    private lateinit var activityRateGameBinding: ActivityRateGameBinding
    public lateinit var game_object : GameModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRateGameBinding = ActivityRateGameBinding.inflate(layoutInflater)
        setContentView(activityRateGameBinding.root)
        determine()
    }

    private fun determine() {
        game_object = intent.extras!!.get("game_object") as GameModel
        Toast.makeText(this, "${game_object.title}", Toast.LENGTH_SHORT).show()
        val navControl = Navigation.findNavController(this, R.id.activity_rate_game_fragment)
        NavigationUI.setupActionBarWithNavController(this, navControl)


    }

}