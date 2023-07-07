package com.yusufcansenturk.moviecatchapp.di.dao.favoriteList

import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
){

    val favoriteAllData: List<FavoriteData> = favoriteDao.readAllData()

    fun addMovieFavorite(favoriteData: FavoriteData) {
        favoriteDao.addFavorite(favoriteData)
    }

}