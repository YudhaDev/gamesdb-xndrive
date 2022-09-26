package com.xndrive.gamesdb.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationBarView
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.databinding.ActivityHomeBinding
import com.xndrive.gamesdb.etc.adapter.AdapterHomeRecycler
import com.xndrive.gamesdb.models.data.GameModel
import com.xndrive.gamesdb.views.fragments.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private var activityHomeBinding : ActivityHomeBinding? = null
    private var gameData: ArrayList<GameModel>? = null

    private var backkey_pressed = -1

    //fragments
    private val newestFragment = NewestFragment()
    private val trendingFragment = TrendingFragment()
    private val favouriteFragment = FavouriteFragment()

    companion object {
        val HOMEACTIVITY_BUNDLEKEY = "game-bundle"
        val HOMEACTIVITY_EXTRAKEY = "game-extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)
        
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding!!.root)
        
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setTitle("Game List")

        determine()
        initRecyclerview()
    }

    private fun initRecyclerview() {
        val adapter = AdapterHomeRecycler(applicationContext, gameData!!.sortedBy { it.title })
        adapter.setOnItemClickCallback(object : AdapterHomeRecycler.onItemClickCallback {
            override fun onItemclicked(data: GameModel) {
                val bundle = Bundle()
                bundle.putSerializable(HOMEACTIVITY_BUNDLEKEY, data)
                val intent = Intent(applicationContext, GameDetailActivity::class.java)
                intent.putExtra(HOMEACTIVITY_EXTRAKEY, bundle)
                startActivity(intent)
            }

        })
        with(activityHomeBinding!!.activityHomeGamesRecyclerview){
            this.adapter = adapter
            //Linear
            val layoutManager = LinearLayoutManager(applicationContext)
            this.layoutManager = layoutManager
            this.addItemDecoration(
                DividerItemDecoration(
                    applicationContext,
                    layoutManager.orientation
                )
            )
        }

    }

    private fun determine() {
        activityHomeBinding!!.activityHomePilihjenisTampilanTextview.setOnClickListener(this)
        gameData = ArrayList()
        with(gameData!!) {
            this.add(
                GameModel(
                    "Grand Theft Auto V",
                    "Rockstar North",
                    resources.getString(R.string.gtav_description),
                    "https://cdn.thegamesdb.net/images/thumb/boxart/front/20952-1.jpg",
                    "https://cdn.thegamesdb.net/images/medium/fanart/20952-1.jpg",
                    "Action | Open World"
                )
            )
            this.add(
                GameModel(
                    "Elden Ring",
                    "FromSoftware, Inc.",
                    resources.getString(R.string.elden_description),
                    "https://cdn.thegamesdb.net/images/thumb/boxart/front/65101-1.jpg",
                    "https://cdn.thegamesdb.net/images/medium/screenshot/65101-1.jpg",
                    "Open World | RPG | Action | Fantasy"
                ),
            )
            this.add(
                GameModel(
                    "Dark Souls III",
                    "FromSoftware, Inc.",
                    resources.getString(R.string.ds3_description),
                    "https://cdn.thegamesdb.net/images/thumb/boxart/front/35414-1.jpg",
                    "https://cdn.thegamesdb.net/images/medium/fanart/35414-1.jpg",
                    "Adventure | Fantasy | RPG"
                ),
            )
            this.add(
                GameModel(
                    "Cyberpunk 2077",
                    "CD Projekt RED Sp. z o.o.",
                    resources.getString(R.string.cp_description),
                    "https://cdn.thegamesdb.net/images/thumb/boxart/front/14517-1.jpg",
                    "https://cdn.thegamesdb.net/images/medium/fanart/14517-1.jpg",
                    "RPG | Scifi | Action | Shooter"
                ),
            )
            this.add(
                GameModel(
                    "No Man's Sky",
                    "Hello Games",
                    resources.getString(R.string.nms_description),
                    "https://cdn.thegamesdb.net/images/thumb/boxart/front/18751-1.jpg",
                    "https://cdn.thegamesdb.net/images/medium/fanart/18751-1.jpg",
                    "Adventure | Scifi | "
                ),
            )
            this.add(
                GameModel(
                    "The Witcher 3: Wild Hunt",
                    "CD Projekt RED Sp. z o.o.",
                    resources.getString(
                        R.string.witcher_description
                    ),
                    "https://cdn.thegamesdb.net/images/thumb/boxart/front/33255-1.jpg",
                    "https://cdn.thegamesdb.net/images/medium/fanart/33255-1.jpg",
                    null
                ),
            )
            this.add(
                GameModel(
                    "Dyson Sphere Program",
                    "Youthcat Studio",
                    resources.getString(R.string.dsp_description),
                    "https://cdn.thegamesdb.net/images/thumb/boxart/front/85550-1.jpg",
                    "https://cdn.cloudflare.steamstatic.com/steam/apps/1366540/header.jpg",
                    null
                ),
            )
            this.add(
                GameModel(
                    "Cities: Skylines",
                    "Colossal Order",
                    resources.getString(R.string.cities_description),
                    "https://cdn.thegamesdb.net/images/thumb/boxart/front/25473-1.jpg",
                    "https://cdn.thegamesdb.net/images/medium/screenshots/25473-1.jpg",
                    null
                ),
            )
            this.add(
                GameModel(
                    "Terraria",
                    "RE-LOGIC",
                    resources.getString(R.string.terraria_description),
                    "https://cdn.thegamesdb.net/images/thumb/boxart/front/2642-1.jpg",
                    "https://cdn.thegamesdb.net/images/medium/fanart/2642-1.jpg",
                    null
                ),
            )
            this.add(
                GameModel(
                    "The Elder Scrolls V: Skyrim Special Edition",
                    "Bethesda Game Studios",
                    resources.getString(
                        R.string.sse_description
                    ),
                    "https://cdn.thegamesdb.net/images/thumb/boxart/front/53306-1.jpg",
                    "https://cdn.thegamesdb.net/images/medium/screenshot/53306-1.jpg",
                    null
                ),
            )
            this.add(
                GameModel(
                    "Fallout 4",
                    "Bethesda Game Studios",
                    resources.getString(R.string.sse_description),
                    "https://cdn.thegamesdb.net/images/thumb/boxart/front/29227-1.jpg",
                    "https://cdn.thegamesdb.net/images/medium/fanart/29227-1.jpg",
                    null
                ),
            )

        }
        
        handleBottomnavClick()
    }

    private fun handleBottomnavClick() {
        activityHomeBinding!!.activityHomeBottomnav.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.bottomnav_menus_newest -> {
//                        Toast.makeText(this@HomeActivity, "newest diklik", Toast.LENGTH_SHORT).show()
                        supportFragmentManager.beginTransaction().replace(R.id.activity_home_fragment_container, newestFragment).commit()
                        return true
                    }
                    
                    R.id.bottomnav_menus_trending -> {
//                        Toast.makeText(this@HomeActivity, "trending diklik", Toast.LENGTH_SHORT).show()
                        supportFragmentManager.beginTransaction().replace(R.id.activity_home_fragment_container, trendingFragment).commit()
                        return true
                    }
                    
                    R.id.bottomnav_menus_favourite -> {
//                        Toast.makeText(this@HomeActivity, "favourite", Toast.LENGTH_SHORT).show()
                        supportFragmentManager.beginTransaction().replace(R.id.activity_home_fragment_container, favouriteFragment).commit()
                        return true
                    }
                    else -> {
                        return false
                    }
                }
            }

        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actionbar_more, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionbar_options -> {
                startActivity(Intent(applicationContext, AboutActivity::class.java))
                return true
            }
            R.id.actionbar_user_setting -> {
                startActivity(Intent(applicationContext, UserActivity::class.java))
                return true
            }
            else -> {
                return true
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.activity_home_pilihjenisTampilan_textview -> {
                val popUpMenu =
                    PopupMenu(applicationContext, activityHomeBinding!!.activityHomePilihjenisTampilanTextview)
                popUpMenu.menuInflater.inflate(R.menu.options_pilihtampilan, popUpMenu.menu)
                popUpMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(p0: MenuItem?): Boolean {
                        when (p0!!.itemId) {
                            R.id.options_pilihtampilan_linear -> {
                                val linearLayoutManager = LinearLayoutManager(applicationContext)
                                with(activityHomeBinding!!.activityHomeGamesRecyclerview){
                                    this.layoutManager =
                                        linearLayoutManager
                                    this.adapter!!.notifyDataSetChanged()
                                }

                                return true
                            }
                            R.id.options_pilihtampilan_grid -> {
                                val gridLayoutManager = GridLayoutManager(applicationContext, 2)
                                with(activityHomeBinding!!.activityHomeGamesRecyclerview){
                                    this.layoutManager = gridLayoutManager
                                    this.adapter!!.notifyDataSetChanged()
                                }
                                return true
                            }
                            else -> {
                                return true
                            }
                        }
                    }

                })
                popUpMenu.show()
            }
        }
    }

    override fun onBackPressed() {
        if (backkey_pressed < 0) {
            Toast.makeText(this, getString(R.string.back_key_exit_text), Toast.LENGTH_SHORT).show()
            backkey_pressed++
            Handler().postDelayed(
                {
                    backkey_pressed = -1
                }, 2000
            )
        } else {
            finish()
        }
//        super.onBackPressed()
    }
}