package com.yusufcansenturk.moviecatchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.moviecatchapp.databinding.ItemCollectionBinding
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.CollectionData
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.Collection
import com.yusufcansenturk.moviecatchapp.util.Constants.BASE_IMAGE_URL

class CollectionAdapter(
    private val collection: List<Collection>,
    private val collectionData: List<CollectionData>
) : RecyclerView.Adapter<CollectionAdapter.MyCustomHolder>() {

    class MyCustomHolder(val binding: ItemCollectionBinding) : RecyclerView.ViewHolder(binding.root) {

        private val imageViews : List<ImageView> = listOf(
            binding.moviePoster1,
            binding.moviePoster2,
            binding.moviePoster3,
            binding.moviePoster4,
            binding.moviePoster5,
            binding.moviePoster6,
        )

        fun bindItem(collectionName:Collection, collectionData: CollectionData) {
            binding.collectionName.text = collectionData.collectionName
            val lastSixPosters = collectionData.poster_path!!.takeLast(6)

            for (i in lastSixPosters.indices) {
                val posterUrl = BASE_IMAGE_URL+lastSixPosters[i]
                val imageView = imageViews[i]
                Glide.with(imageView).load(posterUrl).into(imageView)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCollectionBinding.inflate(inflater, parent, false)
        return MyCustomHolder(binding)
    }

    override fun getItemCount(): Int {
        return collection.size
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        val collectionName = collection[position]
        val collectionData = collectionData[position]

        holder.bindItem(collectionName, collectionData)
    }
}