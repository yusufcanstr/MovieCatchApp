package com.yusufcansenturk.moviecatchapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcansenturk.moviecatchapp.di.retrofit.RetrofitRepository
import com.yusufcansenturk.moviecatchapp.model.Genre
import com.yusufcansenturk.moviecatchapp.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val repository: RetrofitRepository
) : ViewModel() {
    var popularMovieList : MutableLiveData<Movie>
    var recentMovieList : MutableLiveData<Movie>
    var genreList : MutableLiveData<Genre>


    init {
        popularMovieList = MutableLiveData()
        recentMovieList = MutableLiveData()
        genreList = MutableLiveData()
    }

    fun getObserveGenre (): MutableLiveData<Genre>{
        return genreList
    }


    fun getObserveLiveData(isPopular: Boolean): MutableLiveData<Movie>{
        if (isPopular)
            return popularMovieList
        else
            return recentMovieList
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

}