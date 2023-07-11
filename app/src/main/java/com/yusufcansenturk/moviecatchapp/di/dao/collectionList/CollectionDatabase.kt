package com.yusufcansenturk.moviecatchapp.di.dao.collectionList

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[CollectionData::class], version = 1, exportSchema = false)
abstract class CollectionDatabase : RoomDatabase(){

    abstract fun getDao() : CollectionDao

    companion object {
        private var dbINSTANCE : CollectionDatabase? = null

        fun getCollectionDB(context: Context): CollectionDatabase {
            if (dbINSTANCE == null) {
                dbINSTANCE = Room.databaseBuilder<CollectionDatabase>(
                    context.applicationContext,
                    CollectionDatabase::class.java,
                    "CollectionDB"
                ).allowMainThreadQueries().build()
            }
            return dbINSTANCE!!
        }
    }

}