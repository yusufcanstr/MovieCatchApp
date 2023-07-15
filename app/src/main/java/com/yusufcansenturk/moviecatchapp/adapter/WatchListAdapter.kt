package com.yusufcansenturk.moviecatchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.moviecatchapp.databinding.ItemFavoriteBinding
import com.yusufcansenturk.moviecatchapp.databinding.ItemWatchMovieBinding
import com.yusufcansenturk.moviecatchapp.di.dao.favoriteList.FavoriteData
import com.yusufcansenturk.moviecatchapp.di.dao.watchList.WatchData
import com.yusufcansenturk.moviecatchapp.ui.fragments.home.pages.FavoriteFragmentDirections
import com.yusufcansenturk.moviecatchapp.util.Constants.BASE_IMAGE_URL
import com.yusufcansenturk.moviecatchapp.util.FavoriteClickType

class WatchListAdapter(
    private var watchList: List<WatchData>,
    private var onItemClick: (movieList: WatchData, type: FavoriteClickType) -> Unit,
) : RecyclerView.Adapter<WatchListAdapter.MyCustomHolder>() {
    class MyCustomHolder(val binding: ItemWatchMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:WatchData) {
            Glide
                .with(binding.watchPoster)
                .load(BASE_IMAGE_URL+data.poster_path)
                .into(binding.watchPoster)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view = ItemWatchMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(view)
    }

    override fun getItemCount(): Int {
        return watchList.size
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(watchList[position])
        holder.binding.apply {
            watchPoster.setOnClickListener {
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToFilmDetailsFragment(watchList[position].movie_id)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    fun updateData(newMovieList: List<WatchData>) {
        watchList = newMovieList
        notifyDataSetChanged()
    }
}