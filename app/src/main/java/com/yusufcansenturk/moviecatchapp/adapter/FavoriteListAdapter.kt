package com.yusufcansenturk.moviecatchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.moviecatchapp.databinding.ItemFavoriteBinding
import com.yusufcansenturk.moviecatchapp.di.dao.favoriteList.FavoriteData
import com.yusufcansenturk.moviecatchapp.ui.fragments.home.pages.FavoriteFragmentDirections
import com.yusufcansenturk.moviecatchapp.ui.fragments.home.pages.HomeFragmentDirections
import com.yusufcansenturk.moviecatchapp.util.Constants.BASE_IMAGE_URL
import com.yusufcansenturk.moviecatchapp.util.FavoriteClickType

class FavoriteListAdapter(
    private var favoriteList: List<FavoriteData>,
    private var onItemClick: (movieList: FavoriteData, type: FavoriteClickType) -> Unit,
) : RecyclerView.Adapter<FavoriteListAdapter.MyCustomHolder>() {
    class MyCustomHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:FavoriteData) {
            binding.txtTitle.text = data.name
            binding.txtTime.text = data.runtime.toString() + " min"
            binding.txtOverview.text = data.overview
            binding.txtVoteAverage.text = data.vote_average.toString()
            Glide
                .with(binding.posterView)
                .load(BASE_IMAGE_URL+data.poster_path)
                .into(binding.posterView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(view)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(favoriteList[position])
        holder.binding.apply {
            btnDelete.setOnClickListener {
                onItemClick(favoriteList[position], FavoriteClickType.DELETE)
            }
            itemFavorite.setOnClickListener {
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToFilmDetailsFragment(favoriteList[position].movie_id)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    fun updateData(newMovieList: List<FavoriteData>) {
        favoriteList = newMovieList
        notifyDataSetChanged()
    }
}