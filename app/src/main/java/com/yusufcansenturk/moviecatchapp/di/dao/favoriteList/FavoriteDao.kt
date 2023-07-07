package com.yusufcansenturk.moviecatchapp.di.dao.favoriteList

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(favoriteData: FavoriteData)

    @Query("SELECT * FROM favorite_movie_tbl WHERE favorite_status = 1")
    fun readAllData() : List<FavoriteData>

}