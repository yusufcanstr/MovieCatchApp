package com.yusufcansenturk.moviecatchapp.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.yusufcansenturk.moviecatchapp.adapter.SearchAdapter
import com.yusufcansenturk.moviecatchapp.databinding.FragmentSearchBinding
import com.yusufcansenturk.moviecatchapp.di.dao.genre.GenreData
import com.yusufcansenturk.moviecatchapp.util.UiState
import com.yusufcansenturk.moviecatchapp.util.hide
import com.yusufcansenturk.moviecatchapp.util.show
import com.yusufcansenturk.moviecatchapp.util.toast
import com.yusufcansenturk.moviecatchapp.viewmodel.GenreViewModel
import com.yusufcansenturk.moviecatchapp.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: HomePageViewModel by viewModels()
    private val genreViewModel: GenreViewModel by viewModels()
    private var genreList: List<GenreData>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSearch.setOnClickListener {
            if (binding.searchEditText.equals("")) {
                toast("Enter search text !")
            }else {
                searchMovieDataObserver(searchString = binding.searchEditText.text.toString())
            }
        }

    }

    private fun searchMovieDataObserver(searchString: String) {
        viewModel.searchMovieData(searchString)
        viewModel.loadGenreData()
        genreViewModel.getRecordsObserver().observe(viewLifecycleOwner) {
            it?.let {
                genreList = it
            }
        }
        viewModel.searchListData.observe(viewLifecycleOwner) { state ->
            state?.let {
                when(state) {
                    is UiState.Failure -> {
                        binding.searchList.hide()
                        binding.progressBar.hide()
                        binding.imgErrorMessage.show()
                    }
                    is UiState.Loading -> {
                        binding.searchList.hide()
                        binding.progressBar.show()
                        binding.imgErrorMessage.hide()
                    }
                    is UiState.Success -> {
                        binding.searchList.show()
                        binding.progressBar.hide()
                        binding.imgErrorMessage.hide()
                        binding.searchList.apply {
                            adapter = SearchAdapter(state.data.results,genreList!!)
                            binding.searchList.adapter = adapter
                            binding.searchList.layoutManager = GridLayoutManager(requireContext(),1)
                        }
                    }
                    else -> {}
                }
            }
        }
    }

}