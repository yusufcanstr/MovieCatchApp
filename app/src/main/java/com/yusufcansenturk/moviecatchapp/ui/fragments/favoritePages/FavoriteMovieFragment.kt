package com.yusufcansenturk.moviecatchapp.ui.fragments.favoritePages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufcansenturk.moviecatchapp.adapter.FavoriteListAdapter
import com.yusufcansenturk.moviecatchapp.databinding.FragmentFavoriteMovieBinding
import com.yusufcansenturk.moviecatchapp.util.FavoriteClickType
import com.yusufcansenturk.moviecatchapp.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"

@AndroidEntryPoint
class FavoriteMovieFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMovieBinding
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter : FavoriteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteListAdapter(emptyList()) { favoriteData, type ->
            when (type) {
                FavoriteClickType.DELETE -> {
                    viewModel.deleteMovie(favoriteData.movie_id)
                }
            }
        }

        binding.favoriteList.adapter = adapter
        binding.favoriteList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.loadFavoriteData()
        viewModel.favoriteData.observe(viewLifecycleOwner) { movieList ->
            movieList?.let {
                adapter.updateData(movieList)
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            FavoriteMovieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

}