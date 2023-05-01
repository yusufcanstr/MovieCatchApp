package com.yusufcansenturk.moviecatchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yusufcansenturk.moviecatchapp.adapter.MovieAdapter
import com.yusufcansenturk.moviecatchapp.adapter.RecentMovieAdapter
import com.yusufcansenturk.moviecatchapp.databinding.ActivityMainBinding
import com.yusufcansenturk.moviecatchapp.model.Movie
import com.yusufcansenturk.moviecatchapp.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}