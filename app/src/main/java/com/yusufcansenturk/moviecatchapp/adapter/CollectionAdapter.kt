package com.yusufcansenturk.moviecatchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.moviecatchapp.databinding.ItemCollectionBinding
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.Collection
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.CollectionData
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.CollectionWithCollectionData
import com.yusufcansenturk.moviecatchapp.util.Constants.BASE_IMAGE_URL
import com.yusufcansenturk.moviecatchapp.util.FavoriteClickType

class CollectionAdapter(
    private val collection: List<Collection>,
    private var onItemClick: (collection:Collection, type: FavoriteClickType) -> Unit,
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

        fun bindItem(collection: Collection) {
            binding.collectionName.text = collection.collectionName.toString()

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
        val collection = collection[position]
        holder.bindItem(collection)
        holder.binding.apply {
            btnCollectionDelete.setOnClickListener {
                onItemClick(collection,FavoriteClickType.COLLECTION_DELETE)
            }
        }
    }
}