package com.yusufcansenturk.moviecatchapp.di.dao.collectionList

import androidx.sqlite.db.SimpleSQLiteQuery
import javax.inject.Inject

class CollectionRepository @Inject constructor(
    private val collectionDao: CollectionDao
) {
    fun createTable(tableName: String) {
        val formattedTableName = tableName.replace("\\s".toRegex(), "_")
        val createTableQuery = SimpleSQLiteQuery("CREATE TABLE IF NOT EXISTS $formattedTableName " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "movie_id INTEGER, " +
                "imdb_id TEXT, " +
                "name TEXT, " +
                "runtime INTEGER, " +
                "backdrop_path TEXT, " +
                "poster_path TEXT, " +
                "overview TEXT, " +
                "vote_average REAL, " +
                "vote_count INTEGER, " +
                "collection_status INTEGER DEFAULT 0)")
        collectionDao.createTable(createTableQuery)
    }

}