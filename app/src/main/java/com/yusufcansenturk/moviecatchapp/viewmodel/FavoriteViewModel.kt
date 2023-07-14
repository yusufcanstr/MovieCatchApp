package com.yusufcansenturk.moviecatchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.Collection
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.CollectionData
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.CollectionRepository
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.CollectionWithCollectionData
import com.yusufcansenturk.moviecatchapp.di.dao.favoriteList.FavoriteData
import com.yusufcansenturk.moviecatchapp.di.dao.favoriteList.FavoriteRepository
import com.yusufcansenturk.moviecatchapp.di.dao.watchList.WatchData
import com.yusufcansenturk.moviecatchapp.di.dao.watchList.WatchRepository
import com.yusufcansenturk.moviecatchapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val watchRepository: WatchRepository,
    private val collectionRepository: CollectionRepository
) : ViewModel() {

    private val _favoriteData = MutableLiveData<List<FavoriteData>>()
    val favoriteData: LiveData<List<FavoriteData>>
        get() = _favoriteData

    private val _watchData = MutableLiveData<List<WatchData>>()
    val watchData: LiveData<List<WatchData>>
        get() = _watchData

    private val _collection = MutableLiveData<List<Collection>>()
    val collection : LiveData<List<Collection>>
        get() = _collection

    private val _collectionData = MutableLiveData<UiState<List<CollectionData>>>()
    val collectionData : LiveData<UiState<List<CollectionData>>>
        get() = _collectionData

    init {
        loadWatchData()
        loadFavoriteData()
        loadCollection()
    }

    fun addFavoriteMovie(favoriteData: FavoriteData) {
        favoriteRepository.addMovieFavorite(favoriteData)
    }

    fun loadFavoriteData() {
        favoriteRepository.favoriteAllData.observeForever { favoriteDataList ->
            _favoriteData.value = favoriteDataList
        }
    }

    fun deleteMovie(movie_id: Int) {
        viewModelScope.launch {
            favoriteRepository.deleteMovie(movie_id)
        }
    }

    fun addMovieWatchList(watchData: WatchData) {
        watchRepository.addMovieWatchList(watchData)
    }

    fun loadWatchData() {
        watchRepository.watchAllData.observeForever { watchDataList ->
            _watchData.value = watchDataList
        }
    }

    fun deleteMovieWatchList(movie_id: Int) {
        viewModelScope.launch {
            watchRepository.deleteMovieWatchList(movie_id)
        }
    }

    fun createCollection(collectionName :String) {
        viewModelScope.launch {
            collectionRepository.createCollection(
                Collection(collectionName)
            )
        }
    }

    fun loadCollection() {
        collectionRepository.collectionList.observeForever{ collectionList ->
            _collection.value = collectionList
        }
    }

    fun loadCollectionData(collectionName:String) {
        viewModelScope.launch {
            collectionRepository.getCollectionData(collectionName).observeForever { collectionData ->
                _collectionData.value = collectionData
            }
        }
    }

    fun addMovieToCollection(collectionData: CollectionData) {
        collectionRepository.addMovieToCollection(collectionData)
    }

    fun deleteCollection(collectionName:String) {
        viewModelScope.launch {
            collectionRepository.deleteCollection(collectionName)
        }
    }

}