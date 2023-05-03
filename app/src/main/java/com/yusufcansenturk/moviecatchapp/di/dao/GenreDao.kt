package com.yusufcansenturk.moviecatchapp.di.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.Objects

@Dao
interface GenreDao {

    @Insert
    fun addGenre(genre: GenreData)

    @Insert
    fun addAllGenres(objects: List<GenreData>)

    @Query("SELECT * FROM genres")
    fun readAllData(): LiveData<List<GenreData>>

}