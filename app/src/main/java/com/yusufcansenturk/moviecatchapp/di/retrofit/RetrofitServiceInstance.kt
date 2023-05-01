package com.yusufcansenturk.moviecatchapp.di.retrofit

import com.yusufcansenturk.moviecatchapp.model.Genre
import com.yusufcansenturk.moviecatchapp.model.Movie
import com.yusufcansenturk.moviecatchapp.model.Trailer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInstance {

    @GET("3/movie/popular?api_key=4f9a882954141dfe0f929b1d33cc6686")
    fun getPopularVideos(@Query("page") query: String): Call<Movie>

    @GET("3/movie/now_playing?api_key=4f9a882954141dfe0f929b1d33cc6686")
    fun getRecentVideos(@Query("page") query: String): Call<Movie>

    @GET("3/genre/movie/list?api_key=4f9a882954141dfe0f929b1d33cc6686")
    fun getGenres(): Call<Genre>

    @GET("3/movie/{id}/videos?api_key=4f9a882954141dfe0f929b1d33cc6686&id=2")
    fun getTrailerTeasers(@Path("id") id: Int):Call<Trailer>

    @GET("3/genre/movie/list?api_key=4f9a882954141dfe0f929b1d33cc6686&language=en-US")
    fun getSuggestions(@Query("query") query: String): Call<Movie>

}