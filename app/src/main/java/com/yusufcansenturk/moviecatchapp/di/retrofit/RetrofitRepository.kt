package com.yusufcansenturk.moviecatchapp.di.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yusufcansenturk.moviecatchapp.model.Genre
import com.yusufcansenturk.moviecatchapp.model.Movie
import com.yusufcansenturk.moviecatchapp.model.MovieDetail
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

    fun getMoviesDetails(movie_id : Int, liveData: MutableLiveData<MovieDetail>) {
        retrofitServiceInstance.getMovieDetails(movie_id).enqueue(object : Callback<MovieDetail>{
            override fun onResponse(
                call: Call<MovieDetail>,
                response: Response<MovieDetail>,
            ) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

    fun getSearchMovieList(queryString: String): LiveData<Movie> {
        val liveData = MutableLiveData<Movie>()

        retrofitServiceInstance.getSearchMovieList(queryString).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                } else {

                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                // Handle failure
            }
        })

        return liveData
    }
}