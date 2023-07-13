package com.yusufcansenturk.moviecatchapp.util

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    val THEME_KEY = "isDarkMode"
    val FIRST_RUN_KEY = "isFirstRun"
    val PREF_NAME = "moviePref"
    const val FAVORITE_DB_NAME = "favorite_movie_tbl"
    const val WATCH_DB_NAME = "watch_movie_tbl"
    const val COLLECTION_NAME = "collectionDB"
    const val COLLECTION_DATA = "collection_data_tbl"
    const val COLLECTION = "collection_tbl"
}
enum class FavoriteTabs(val index: Int, val key: String) {
    FAVORITE(0, "FavoriteData"),
    WATCHLIST(1, "WatchList"),
    MOVIE_LISTS(2, "MovieLists")
}

enum class FavoriteClickType {
    DELETE,POSTER,COLLECTION_DELETE
}