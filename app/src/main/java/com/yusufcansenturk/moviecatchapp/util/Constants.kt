package com.yusufcansenturk.moviecatchapp.util

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    val THEME_KEY = "isDarkMode"
    val FIRST_RUN_KEY = "isFirstRun"
    val PREF_NAME = "moviePref"
}
enum class FavoriteTabs(val index: Int, val key: String) {
    FAVORITE(0, "Favorite"),
    WATCHLIST(1, "WatchList"),
    MOVIE_LISTS(2, "MovieLists")
}