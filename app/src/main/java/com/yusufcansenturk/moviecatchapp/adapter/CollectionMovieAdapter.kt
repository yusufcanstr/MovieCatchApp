package com.yusufcansenturk.moviecatchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.moviecatchapp.databinding.PopularMovieItemBinding
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.CollectionData
import com.yusufcansenturk.moviecatchapp.util.Constants.BASE_IMAGE_URL

class CollectionMovieAdapter(
    private var collectionList: List<CollectionData>
) : RecyclerView.Adapter<CollectionMovieAdapter.MyCustomHolder>() {
    class MyCustomHolder(val binding: PopularMovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data:CollectionData) {
            binding.txtTitle.text = data.name
            binding.txtGenre.text = ""
            Glide.with(binding.posterView).load(BASE_IMAGE_URL+data.poster_path).into(binding.posterView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view = PopularMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(view)
    }

    override fun getItemCount(): Int {
        return collectionList.size
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(collectionList[position])
        holder.binding.apply {
            posterView.setOnClickListener {

            }
        }
    }
}