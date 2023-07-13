package com.yusufcansenturk.moviecatchapp.di.dao.collectionList

import androidx.lifecycle.LiveData
import javax.inject.Inject

class CollectionRepository @Inject constructor(
    private val collectionDao: CollectionDao
) {

    val collectionList : LiveData<List<Collection>> = collectionDao.getCollectionList()

    fun createCollection(collection: Collection) {
        collectionDao.addCollection(collection)
    }

    fun getCollectionDataList(collectionName:String) : LiveData<List<CollectionWithCollectionData>>{
        return collectionDao.getCollectionWithCollectionData(collectionName)
    }

}