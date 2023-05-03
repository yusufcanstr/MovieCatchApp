package com.yusufcansenturk.moviecatchapp.di.module

import android.app.Application
import com.yusufcansenturk.moviecatchapp.di.dao.GenreDao
import com.yusufcansenturk.moviecatchapp.di.dao.GenreDatabase
import com.yusufcansenturk.moviecatchapp.di.retrofit.RetrofitServiceInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val BASE_URL = "https://api.themoviedb.org/"

    @Provides
    @Singleton
    fun getAppDB(context: Application): GenreDatabase {
        return GenreDatabase.getAppDB(context)
    }


    @Provides
    @Singleton
    fun getDao(appDB: GenreDatabase): GenreDao {
        return appDB.getDAO()
    }

    @Provides
    @Singleton
    fun getRetrofitServiceInstance(retrofit: Retrofit) : RetrofitServiceInstance {
        return retrofit.create(RetrofitServiceInstance::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}