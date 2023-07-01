package com.yusufcansenturk.moviecatchapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.moviecatchapp.R
import com.yusufcansenturk.moviecatchapp.databinding.PopularMovieItemBinding
import com.yusufcansenturk.moviecatchapp.di.dao.GenreData
import com.yusufcansenturk.moviecatchapp.model.Result
import com.yusufcansenturk.moviecatchapp.ui.fragments.home.pages.HomeFragmentDirections
import com.yusufcansenturk.moviecatchapp.util.Constants.BASE_IMAGE_URL
import java.util.*

class MovieAdapter(private val isFirstScreen : Boolean = true): RecyclerView.Adapter<MovieAdapter.MyCustomHolder>() {

    var liveData: List<Result>? = null
    var genreList: List<GenreData>? = null

    //Image
    // https://image.tmdb.org/t/p/w500/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg


    fun setLists(liveData: List<Result>, genreList: List<GenreData>) {
        this.liveData = liveData
        this.genreList = genreList
        notifyDataSetChanged()
    }

    class MyCustomHolder(val binding: PopularMovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data:Result, genreList: List<GenreData>) {
            binding.txtTitle.text = data.title
            binding.txtGenre.text = "genreList."

            val lang = Locale.getDefault().language
            var genres = ""
            for(id in data.genre_ids) {
                val result = genreList.find { x -> x.genre_id == id }
                result?.let {
                    if (lang == "tr") {
                        genres += result!!.tr_name + ", "
                    }else {
                        genres += result!!.en_name + ", "
                    }
                }
            }

            genres = genres.substring(0, genres.length -2)
            binding.txtGenre.text = genres
            Glide.with(binding.posterView).load(BASE_IMAGE_URL+data.poster_path).into(binding.posterView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view = PopularMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyCustomHolder(view)
    }

    override fun getItemCount(): Int {
        return if (liveData==null) {
            0
        }else if (isFirstScreen) {
            10
        }else {
            liveData!!.size
        }
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(liveData!![position], genreList!!)
        holder.binding.apply {
            posterView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToFilmDetailsFragment(liveData!![position].id)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}