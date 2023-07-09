package com.yusufcansenturk.moviecatchapp.di.dao.watchList

import javax.inject.Inject

class WatchRepository @Inject constructor(
    private val watchDao: WatchDao
){

    fun addMovieWatchList(watchData: WatchData) {
        watchDao.addWatchList(watchData)
    }

}