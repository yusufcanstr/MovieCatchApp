package com.yusufcansenturk.moviecatchapp.di.dao.watchList

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWatchList(watchData: WatchData)

    @Query("SELECT * FROM watch_movie_tbl WHERE watch_status = 1")
    fun readAllData() : LiveData<List<WatchData>>
}