package com.yusufcansenturk.moviecatchapp.di.dao.collectionList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yusufcansenturk.moviecatchapp.util.Constants.COLLECTION_DATA
import com.yusufcansenturk.moviecatchapp.util.Constants.COLLECTION_NAME

@Entity(tableName = COLLECTION_DATA)
data class CollectionData(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "collectionName") val collectionName:String,
    @ColumnInfo(name = "movie_id") val movie_id: Int,
    @ColumnInfo(name = "imdb_id") val imdb_id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "runtime") val runtime: Int,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String?,
    @ColumnInfo(name = "poster_path") val poster_path: String?,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "vote_average") val vote_average: Double,
    @ColumnInfo(name = "vote_count") val vote_count: Int,
    @ColumnInfo(name = "collection_status") val collection_status: Boolean = false
)
