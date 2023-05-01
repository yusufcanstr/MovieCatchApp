package com.yusufcansenturk.moviecatchapp.di.retrofit

import androidx.lifecycle.MutableLiveData
import com.yusufcansenturk.moviecatchapp.model.Genre
import com.yusufcansenturk.moviecatchapp.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val retrofitServiceInstance: RetrofitServiceInstance
) {

    fun getPopularMovies(page:String, liveData: MutableLiveData<Movie>) {
        retrofitServiceInstance.getPopularVideos(page).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

    fun getRecentMovies(page:String, liveData:MutableLiveData<Movie>) {
        retrofitServiceInstance.getRecentVideos(page).enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

    fun getAllGenres(liveData:MutableLiveData<Genre>) {
        retrofitServiceInstance.getGenres().enqueue(object : Callback<Genre>{
            override fun onResponse(call: Call<Genre>, response: Response<Genre>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Genre>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

}