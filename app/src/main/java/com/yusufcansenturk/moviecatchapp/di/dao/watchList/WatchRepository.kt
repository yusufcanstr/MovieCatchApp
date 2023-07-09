package com.yusufcansenturk.moviecatchapp.di.dao.watchList

import androidx.lifecycle.LiveData
import javax.inject.Inject

class WatchRepository @Inject constructor(
    private val watchDao: WatchDao
){

    val watchAllData : LiveData<List<WatchData>> = watchDao.readAllData()

    fun addMovieWatchList(watchData: WatchData) {
        watchDao.addWatchList(watchData)
    }

}