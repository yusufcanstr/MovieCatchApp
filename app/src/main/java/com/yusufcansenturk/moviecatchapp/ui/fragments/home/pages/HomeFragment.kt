package com.yusufcansenturk.moviecatchapp.ui.fragments.home.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufcansenturk.moviecatchapp.adapter.MovieAdapter
import com.yusufcansenturk.moviecatchapp.adapter.RecentMovieAdapter
import com.yusufcansenturk.moviecatchapp.databinding.FragmentHomeBinding
import com.yusufcansenturk.moviecatchapp.di.dao.genre.GenreData
import com.yusufcansenturk.moviecatchapp.viewmodel.GenreViewModel
import com.yusufcansenturk.moviecatchapp.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var genreList: List<GenreData>? = null

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recentMovieAdapter: RecentMovieAdapter
    private lateinit var viewModel: HomePageViewModel
    private lateinit var genreViewModel: GenreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomePageViewModel::class.java]
        genreViewModel = ViewModelProvider(this)[GenreViewModel::class.java]

        initRecyclerViews()

        viewModel.getObserveLiveData(true).observe(viewLifecycleOwner) { movieList ->
            movieList?.let {
                movieAdapter.setLists(it.results, genreList!!)
            }
        }

        viewModel.getObserveLiveData(false).observe(viewLifecycleOwner) { recentMovieList ->
            recentMovieList?.let {
                recentMovieAdapter.setLists(it.results, genreList!!)
            }
        }

        genreViewModel.getRecordsObserver().observe(viewLifecycleOwner) {
            it?.let {
                genreList = it
                fetchMovies()
            }
        }

        binding.btnSearch.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    private fun initRecyclerViews() {

        val lmHorizontal = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val lmVertical = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)


        binding.recyclerView.layoutManager = lmHorizontal
        binding.recentRecyclerView.layoutManager = lmVertical

        movieAdapter = MovieAdapter()
        recentMovieAdapter = RecentMovieAdapter()

        binding.recyclerView.adapter = movieAdapter
        binding.recentRecyclerView.adapter = recentMovieAdapter
    }

    private fun fetchMovies() {
        CoroutineScope(Dispatchers.IO).launch {

            val job1 : Deferred<Unit> = async {
                viewModel.loadData("1",true)
            }

            val job2 : Deferred<Unit> = async {
                viewModel.loadData("1",false)
            }

            job1.await()
            job2.await()

        }
    }

}