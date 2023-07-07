package com.yusufcansenturk.moviecatchapp.di.dao.favoriteList

import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
){

    fun addMovieFavorite(favoriteData: FavoriteData) {
        favoriteDao.addFavorite(favoriteData)
    }

}