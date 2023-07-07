package com.yusufcansenturk.moviecatchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcansenturk.moviecatchapp.di.dao.favoriteList.FavoriteData
import com.yusufcansenturk.moviecatchapp.di.dao.favoriteList.FavoriteRepository
import com.yusufcansenturk.moviecatchapp.di.retrofit.RetrofitRepository
import com.yusufcansenturk.moviecatchapp.model.MovieDetail
import com.yusufcansenturk.moviecatchapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: RetrofitRepository,
) : ViewModel(){

    private val _movieDetails = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail>
        get() = _movieDetails

    fun getMovieDetail(movie_id:Int) {
        repository.getMoviesDetails(movie_id,_movieDetails)
    }


}