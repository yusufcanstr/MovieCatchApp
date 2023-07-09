package com.yusufcansenturk.moviecatchapp.di.dao.watchList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yusufcansenturk.moviecatchapp.util.Constants.WATCH_DB_NAME

@Entity(tableName = WATCH_DB_NAME)
data class WatchData(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "movie_id") val movie_id: Int,
    @ColumnInfo(name = "imdb_id") val imdb_id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String?,
    @ColumnInfo(name = "poster_path") val poster_path: String?,
    @ColumnInfo(name = "watch_status") val watch_status: Boolean = false
)
