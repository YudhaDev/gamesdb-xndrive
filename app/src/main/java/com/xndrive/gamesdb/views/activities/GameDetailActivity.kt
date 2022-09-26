package com.xndrive.gamesdb.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.databinding.ActivityGameDetailBinding
import com.xndrive.gamesdb.models.data.GameModel
import com.xndrive.gamesdb.etc.MyGlideManager

class GameDetailActivity : AppCompatActivity() {
    //    private var detailActivityView: View? = null
    private lateinit var activityGameDetailBinding: ActivityGameDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)
        activityGameDetailBinding = ActivityGameDetailBinding.inflate(layoutInflater)
        setContentView(activityGameDetailBinding!!.root)
//        detailActivityView = inflater.inflate(R.layout.activity_detail, null)
//        setContentView(detailActivityView)

        supportActionBar!!.setTitle("Game Detail")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        determine()
        generate()
    }

    private fun generate() {
        val data = intent.extras!!.get(HomeActivity.HOMEACTIVITY_EXTRAKEY) as GameModel
        activityGameDetailBinding.activityDetailGametitleTextview.setText(data.title)
        activityGameDetailBinding.activityDetailGamedeveloperTextview.setText(data.developer)
        activityGameDetailBinding.activityDetailGamedescriptionTextview.setText(data.description)

        val myGlideManager =
            MyGlideManager(this, data.picture2, activityGameDetailBinding.activityDetailGameimageImageview)
        myGlideManager.generateImage()
    }

    private fun determine() {
        activityGameDetailBinding.activityDetailRatingBtn.setOnClickListener {
            _ ->
            startActivity(Intent(this, RateGameActivity::class.java))
        }
        activityGameDetailBinding.activityDetailBuyBtn.setOnClickListener {
            Toast.makeText(applicationContext, "Fitur masih belum tersedia. ^^", Toast.LENGTH_SHORT)
                .show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actionbar_more, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.actionbar_options -> {
                startActivity(Intent(applicationContext, AboutActivity::class.java))
                return true
            }
            else -> {
                return true
            }
        }
    }
}