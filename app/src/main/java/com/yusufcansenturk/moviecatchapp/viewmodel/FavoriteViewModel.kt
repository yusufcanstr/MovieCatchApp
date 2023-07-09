package com.yusufcansenturk.moviecatchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufcansenturk.moviecatchapp.di.dao.favoriteList.FavoriteData
import com.yusufcansenturk.moviecatchapp.di.dao.favoriteList.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _favoriteData = MutableLiveData<List<FavoriteData>>()
    val favoriteData: LiveData<List<FavoriteData>>
        get() = _favoriteData

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

}