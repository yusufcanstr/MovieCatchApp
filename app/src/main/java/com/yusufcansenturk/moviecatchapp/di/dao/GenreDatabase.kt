package com.yusufcansenturk.moviecatchapp.di.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GenreData::class], version = 1, exportSchema = false)
abstract class GenreDatabase : RoomDatabase(){

    abstract fun getDAO() : GenreDao

    companion object {

        private var dbINSTANCE: GenreDatabase? = null

        fun getAppDB(context: Context): GenreDatabase {
            if (dbINSTANCE == null){
                dbINSTANCE = Room.databaseBuilder<GenreDatabase>(
                    context.applicationContext,
                    GenreDatabase::class.java,
                    "genre_database"
                ).allowMainThreadQueries().build()
            }
            return dbINSTANCE!!
        }

    }


}