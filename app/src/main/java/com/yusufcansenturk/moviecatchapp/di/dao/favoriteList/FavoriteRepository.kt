package com.yusufcansenturk.moviecatchapp.di.dao.favoriteList

import androidx.lifecycle.LiveData
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
){

    val favoriteAllData: LiveData<List<FavoriteData>> = favoriteDao.readAllData()

    fun addMovieFavorite(favoriteData: FavoriteData) {
        favoriteDao.addFavorite(favoriteData)
    }

    fun deleteMovie(movie_id:Int) {
        favoriteDao.deleteMovie(movie_id)
    }

}