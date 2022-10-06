package com.xndrive.gamesdb.etc.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xndrive.gamesdb.views.activities.GameDetailActivity
import com.xndrive.gamesdb.views.activities.HomeActivity
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.models.data.GameModel
import com.xndrive.gamesdb.etc.MyGlideManager

class AdapterHomeRecycler(private val context : Context, private val list : List<GameModel>, private val mode : String) : RecyclerView.Adapter<AdapterHomeRecycler.Viewholder>() {
    private lateinit var onItemCallback : onItemClickCallback

    companion object {
        val HORIZONTAL : String = "horizontal"
        val VERTICAL : String = "vertical"
    }

    fun setOnItemClickCallback(itemcallback : onItemClickCallback){
        this.onItemCallback = itemcallback
    }

    interface onItemClickCallback {
        fun onItemclicked(data : GameModel)
    }
    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.items_gamelayout_title_textview)
        val developer: TextView = itemView.findViewById(R.id.items_gamelayout_developer_textview)
        val imageview : ImageView = itemView.findViewById(R.id.items_gamelayout_imageview)
        val genres : TextView = itemView.findViewById(R.id.items_gamelayout_genre_textview)
//        val genreGroup : ChipGroup = itemView.findViewById(R.id.items_gamelayout_genre_chipgroup)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterHomeRecycler.Viewholder {
        val inflater = LayoutInflater.from(context)
        var view:View? = null
        when(mode){
            HORIZONTAL -> {
                view = inflater.inflate(R.layout.items_gamelayout_horizontal,parent, false)

            }
            VERTICAL -> {
                view = inflater.inflate(R.layout.items_gamelayout,parent, false)

            }
        }
        return Viewholder(view!!)
    }

    override fun onBindViewHolder(holder: AdapterHomeRecycler.Viewholder, position: Int) {
//        val circularProgress = CircularProgressDrawable(this)
//        circularProgress.strokeWidth = 5f
//        circularProgress.centerRadius = 30f
//        circularProgress.start()

        holder.title.setText(list.get(position).title)
        holder.developer.setText(list.get(position).developer)
        holder.genres.setText(list.get(position).genres)
        val myGlideManager = MyGlideManager(context,list.get(position).picture, holder.imageview)
        myGlideManager.generateImage()
//        Glide.with(holder.itemView.context)
//            .load(list.get(position).picture)
//            .apply(RequestOptions().override(80))
//            .placeholder(circularProgress)
//            .into(holder.imageview)

        //cara1
        holder.itemView.setOnClickListener{
            val intent = Intent(context, GameDetailActivity::class.java)
            intent.putExtra(HomeActivity.HOMEACTIVITY_EXTRAKEY, list[position])
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //perlu tambahan flag kalo pindah activity diluar activity class
            context.startActivity(intent)
        }

        //cara2 pake interface


    }

    override fun getItemCount(): Int {
        return 5 //maksimal nampilin 5 aja
//        return list.size
    }
}