package com.yusufcansenturk.moviecatchapp.di.dao.collectionList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yusufcansenturk.moviecatchapp.util.UiState
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

    fun addMovieToCollection(collectionData: CollectionData) {
        collectionDao.addMovieToCollection(collectionData)
    }

    fun deleteCollection(collectionName:String) {
        collectionDao.deleteCollection(collectionName)
        collectionDao.deleteCollectionData(collectionName)
    }

    fun getCollectionData(collectionName: String): LiveData<UiState<List<CollectionData>>> {
        val result = MutableLiveData<UiState<List<CollectionData>>>()

        result.value = UiState.Loading

        val data = collectionDao.getCollectionData(collectionName)

        data.observeForever { collectionData ->
            if (collectionData != null) {
                result.value = UiState.Success(data = collectionData)
            } else {
                result.value = UiState.Failure("Error")
            }
        }

        return result
    }

}