package com.yusufcansenturk.moviecatchapp.di.dao.favoriteList

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(favoriteData: FavoriteData)



}