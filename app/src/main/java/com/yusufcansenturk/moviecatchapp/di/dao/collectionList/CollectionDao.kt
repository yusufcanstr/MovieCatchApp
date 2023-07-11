package com.yusufcansenturk.moviecatchapp.di.dao.collectionList

import androidx.room.Dao
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface CollectionDao {
    @RawQuery
    fun createTable(query: SupportSQLiteQuery): Long

}
