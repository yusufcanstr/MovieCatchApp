package com.yusufcansenturk.moviecatchapp.di.dao.genre

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val genre_id:Int,
    val en_name:String,
    val tr_name:String
)