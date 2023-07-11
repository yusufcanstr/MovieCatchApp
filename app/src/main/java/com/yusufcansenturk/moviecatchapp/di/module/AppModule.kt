package com.yusufcansenturk.moviecatchapp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.CollectionDao
import com.yusufcansenturk.moviecatchapp.di.dao.collectionList.CollectionDatabase
import com.yusufcansenturk.moviecatchapp.di.dao.favoriteList.FavoriteDao
import com.yusufcansenturk.moviecatchapp.di.dao.favoriteList.FavoriteDatabase
import com.yusufcansenturk.moviecatchapp.di.dao.genre.GenreDao
import com.yusufcansenturk.moviecatchapp.di.dao.genre.GenreDatabase
import com.yusufcansenturk.moviecatchapp.di.dao.watchList.WatchDao
import com.yusufcansenturk.moviecatchapp.di.dao.watchList.WatchDatabase
import com.yusufcansenturk.moviecatchapp.di.retrofit.RetrofitServiceInstance
import com.yusufcansenturk.moviecatchapp.prefs.MovieSesionManager
import com.yusufcansenturk.moviecatchapp.util.Constants
import com.yusufcansenturk.moviecatchapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context) =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSessionManager(preferences: SharedPreferences) = MovieSesionManager(preferences)

    @Provides
    @Singleton
    fun getWatchDB(context: Application): WatchDatabase {
        return WatchDatabase.getWatchDB(context)
    }

    @Provides
    @Singleton
    fun getWatchDao(DB: WatchDatabase): WatchDao {
        return DB.getDAO()
    }

    @Provides
    @Singleton
    fun getCollectionDB(context: Application): CollectionDatabase {
        return CollectionDatabase.getCollectionDB(context)
    }

    @Provides
    @Singleton
    fun getCollectionDao(DB: CollectionDatabase): CollectionDao {
        return DB.getDao()
    }

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
    fun getFavoriteDB(context: Application): FavoriteDatabase {
        return FavoriteDatabase.getFavoriteDB(context)
    }

    @Provides
    @Singleton
    fun getFavoriteDao(DB: FavoriteDatabase): FavoriteDao {
        return DB.getDAO()
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