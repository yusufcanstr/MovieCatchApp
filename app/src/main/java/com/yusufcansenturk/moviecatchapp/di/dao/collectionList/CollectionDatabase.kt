package com.yusufcansenturk.moviecatchapp.di.dao.collectionList

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [CollectionData::class, Collection::class], version = 2, exportSchema = false)
abstract class CollectionDatabase : RoomDatabase() {

    abstract fun getDao(): CollectionDao

    companion object {
        private var dbINSTANCE: CollectionDatabase? = null

        fun getCollectionDB(context: Context): CollectionDatabase {
            if (dbINSTANCE == null) {
                dbINSTANCE = Room.databaseBuilder<CollectionDatabase>(
                    context.applicationContext,
                    CollectionDatabase::class.java,
                    "CollectionDB"
                )
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build()
            }
            return dbINSTANCE!!
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}
