package com.yusufcansenturk.moviecatchapp.di.dao.watchList

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface WatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWatchList(watchData: WatchData)
}