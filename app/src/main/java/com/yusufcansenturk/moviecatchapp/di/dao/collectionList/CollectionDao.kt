package com.yusufcansenturk.moviecatchapp.di.dao.collectionList

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SupportSQLiteQuery
import com.yusufcansenturk.moviecatchapp.util.UiState

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCollection(collection: Collection)

    @Query("SELECT * FROM collection_tbl")
    fun getCollectionList() : LiveData<List<Collection>>

    @Transaction
    @Query("SELECT * FROM collection_tbl WHERE collectionName = :collectionName")
    fun getCollectionWithCollectionData(collectionName:String) : LiveData<List<CollectionWithCollectionData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieToCollection(collectionData: CollectionData)

    @Query("DELETE FROM collection_tbl WHERE collectionName = :collectionName")
    fun deleteCollection(collectionName:String)

    @Query("DELETE FROM collection_data_tbl WHERE collectionName = :collectionName")
    fun deleteCollectionData(collectionName:String)

    @Query("SELECT * FROM collection_data_tbl WHERE collectionName = :collectionName GROUP BY imdb_id")
    fun getCollectionData(collectionName: String) :LiveData<List<CollectionData>>

}
