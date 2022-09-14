package com.xndrive.gamesdb.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.models.data.GameModel
import com.xndrive.gamesdb.etc.MyGlideManager

class DetailActivity : AppCompatActivity() {
    private var detailActivityView: View? = null
    private var activity_detail_gameimage_imageview: ImageView? = null
    private var activity_detail_gametitle_textview: TextView? = null
    private var activity_detail_gamedeveloper_textview: TextView? = null
    private var activity_detail_gamedescription_textview: TextView? = null
    private var activity_detail_buy_btn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)
        detailActivityView = inflater.inflate(R.layout.activity_detail, null)
        setContentView(detailActivityView)

        supportActionBar!!.setTitle("Game Detail")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        determine()
        generate()
    }

    private fun generate() {
        val data = intent.extras!!.get(HomeActivity.HOMEACTIVITY_EXTRAKEY) as GameModel
        activity_detail_gametitle_textview!!.setText(data.title)
        activity_detail_gamedeveloper_textview!!.setText(data.developer)
        activity_detail_gamedescription_textview!!.setText(data.description)

        val myGlideManager = MyGlideManager(this, data.picture2, activity_detail_gameimage_imageview!!)
        myGlideManager.generateImage()
    }

    private fun determine() {
        with(detailActivityView!!){
            activity_detail_gameimage_imageview = this.findViewById(R.id.activity_detail_gameimage_imageview)
            activity_detail_gametitle_textview = this.findViewById(R.id.activity_detail_gametitle_textview)
            activity_detail_gamedeveloper_textview = this.findViewById(R.id.activity_detail_gamedeveloper_textview)
            activity_detail_gamedescription_textview = this.findViewById(R.id.activity_detail_gamedescription_textview)
            activity_detail_buy_btn = this.findViewById(R.id.activity_detail_buy_btn)

            activity_detail_buy_btn!!.setOnClickListener {
                Toast.makeText(applicationContext, "Fitur masih belum tersedia. ^^", Toast.LENGTH_SHORT).show()
            }
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