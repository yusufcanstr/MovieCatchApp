package com.yusufcansenturk.moviecatchapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.moviecatchapp.R
import com.yusufcansenturk.moviecatchapp.model.Result


class RecentMovieAdapter(private val isFirstScreen : Boolean = true): RecyclerView.Adapter<RecentMovieAdapter.MyCustomHolder>() {

    var liveData: List<Result>? = null

    //Image
    // https://image.tmdb.org/t/p/w500/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg


    fun setList(liveData: List<Result>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }

    class MyCustomHolder(val view: View) : RecyclerView.ViewHolder(view){

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtGenre = view.findViewById<TextView>(R.id.txtGenre)
        val posterView = view.findViewById<ImageView>(R.id.posterView)
        val txtReleaseDate = view.findViewById<TextView>(R.id.txtReleaseDate)
        val txtVoteAverage = view.findViewById<TextView>(R.id.txtVoteAverage)

        fun bind(data: Result) {
            txtTitle.text = data.title
            txtGenre.text = "Deneme deneme"
            txtReleaseDate.text = data.release_date
            txtVoteAverage.text = data.vote_average.toString() + " / 10"
            Glide.with(posterView).load("https://image.tmdb.org/t/p/w500"+data.poster_path).into(posterView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_movie_item, parent,false)
        return MyCustomHolder(view)
    }

    override fun getItemCount(): Int {
        if (liveData==null) {
            return 0
        }else if (isFirstScreen) {
            return 20
        }else {
            return liveData!!.size
        }
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(liveData!![position])
    }
}