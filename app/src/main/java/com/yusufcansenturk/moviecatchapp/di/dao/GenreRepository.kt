package com.yusufcansenturk.moviecatchapp.di.dao

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val genreDao: GenreDao
) {

    val readAllData: List<GenreData> = genreDao.readAllData()

    fun addGenre(genreData: GenreData) {
        genreDao.addGenre(genreData)
    }

    fun addAllGenres(genreList: List<GenreData>) {
        genreDao.addAllGenres(genreList)
    }


}