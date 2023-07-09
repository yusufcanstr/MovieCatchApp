package com.yusufcansenturk.moviecatchapp.di.dao.watchList

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WatchData::class], version = 1, exportSchema = false)
abstract class WatchDatabase: RoomDatabase() {

    abstract fun getDAO() : WatchDao

    companion object {
        private var dbINSTANCE : WatchDatabase? = null

        fun getWatchDB(context: Context): WatchDatabase {
            if (dbINSTANCE == null) {
                dbINSTANCE = Room.databaseBuilder<WatchDatabase>(
                    context.applicationContext,
                    WatchDatabase::class.java,
                    "watchTable"
                ).allowMainThreadQueries().build()
            }
            return dbINSTANCE!!
        }
    }

}