package com.yusufcansenturk.moviecatchapp.di.dao.collectionList

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import javax.inject.Inject

class CollectionRepository @Inject constructor(
    private val collectionDao: CollectionDao
) {


    fun createCollection(collection: Collection) {
        collectionDao.addCollection(collection)
    }

}