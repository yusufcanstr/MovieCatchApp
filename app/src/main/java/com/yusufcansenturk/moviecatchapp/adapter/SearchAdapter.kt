package com.yusufcansenturk.moviecatchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.moviecatchapp.model.Result
import com.yusufcansenturk.moviecatchapp.databinding.RecentMovieItemBinding
import com.yusufcansenturk.moviecatchapp.di.dao.genre.GenreData
import com.yusufcansenturk.moviecatchapp.ui.fragments.home.SearchFragmentDirections
import com.yusufcansenturk.moviecatchapp.ui.fragments.home.pages.FavoriteFragmentDirections
import com.yusufcansenturk.moviecatchapp.util.Constants.BASE_IMAGE_URL
import java.util.*

class SearchAdapter(
    private var movieList: List<Result>,
    private var genreList: List<GenreData>
) : RecyclerView.Adapter<SearchAdapter.MyCustomHolder>() {


    class MyCustomHolder(val binding: RecentMovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Result, genreList: List<GenreData>) {
            binding.txtTitle.text = data.title
            binding.txtGenre.text = "genreList."

            val lang = Locale.getDefault().language
            var genres = ""
            for(id in data.genreIds) {
                val result = genreList.find { x -> x.genre_id == id }
                result?.let {
                    genres += if (lang == "tr") {
                        result.tr_name + ", "
                    }else {
                        result.en_name + ", "
                    }
                }
            }

            genres = genres.substring(0, genres.length -2)
            binding.txtGenre.text = genres

            binding.txtReleaseDate.text = data.releaseDate
            binding.txtVoteAverage.text = data.voteAverage.toString() + " / 10"
            Glide.with(binding.posterView).load(BASE_IMAGE_URL+data.posterPath).into(binding.posterView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view = RecentMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return SearchAdapter.MyCustomHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(movieList[position], genreList)
        holder.binding.apply {
            recentMovieItem.setOnClickListener {
                val action = SearchFragmentDirections.actionSearchFragmentToFilmDetailsFragment(movieList[position].id)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}