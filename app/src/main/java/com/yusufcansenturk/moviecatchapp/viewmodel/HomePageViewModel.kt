package com.yusufcansenturk.moviecatchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufcansenturk.moviecatchapp.di.retrofit.RetrofitRepository
import com.yusufcansenturk.moviecatchapp.model.Genre
import com.yusufcansenturk.moviecatchapp.model.Movie
import com.yusufcansenturk.moviecatchapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val repository: RetrofitRepository
) : ViewModel() {
    private var popularMovieList : MutableLiveData<Movie> = MutableLiveData()
    private var recentMovieList : MutableLiveData<Movie> = MutableLiveData()
    private var genreList : MutableLiveData<Genre> = MutableLiveData()

    private val _searchListData = MutableLiveData<UiState<Movie>>()
    val searchListData: LiveData<UiState<Movie>>
        get() = _searchListData

    fun getObserveGenre (): MutableLiveData<Genre>{
        return genreList
    }

    fun getObserveLiveData(isPopular: Boolean): MutableLiveData<Movie>{
        return if (isPopular)
            popularMovieList
        else
            recentMovieList
    }

    fun loadGenreData() {
        repository.getAllGenres(genreList)
    }

    fun loadData(page:String, isPopular: Boolean){
        if (isPopular)
            repository.getPopularMovies(page, popularMovieList)
        else
            repository.getRecentMovies(page, recentMovieList)
    }

    fun searchMovieData(searchString: String) {
        _searchListData.value = UiState.Loading
        viewModelScope.launch {
            try {
                val movie = repository.getSearchMovieList(searchString).observeForever {
                    _searchListData.value = UiState.Success(it)
                }
            } catch (e: Exception) {
                _searchListData.value = UiState.Failure(e.message)
            }
        }
    }

}