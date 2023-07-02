package com.yusufcansenturk.moviecatchapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yusufcansenturk.moviecatchapp.ui.fragments.favoritePages.FavoriteMovieFragment
import com.yusufcansenturk.moviecatchapp.ui.fragments.favoritePages.MovieListsFragment
import com.yusufcansenturk.moviecatchapp.ui.fragments.favoritePages.WatchListFragment
import com.yusufcansenturk.moviecatchapp.util.FavoriteTabs

class FavoritePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = FavoriteTabs.values().size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FavoriteTabs.FAVORITE.index -> FavoriteMovieFragment.newInstance(FavoriteTabs.FAVORITE.name)
            FavoriteTabs.WATCHLIST.index -> WatchListFragment.newInstance(FavoriteTabs.WATCHLIST.name)
            FavoriteTabs.MOVIE_LISTS.index -> MovieListsFragment.newInstance(FavoriteTabs.MOVIE_LISTS.name)
            else -> throw IllegalStateException("Fragment not found")
        }
    }

}