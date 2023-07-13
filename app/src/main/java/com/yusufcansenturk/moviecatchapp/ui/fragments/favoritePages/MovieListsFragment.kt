package com.yusufcansenturk.moviecatchapp.ui.fragments.favoritePages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.yusufcansenturk.moviecatchapp.R
import com.yusufcansenturk.moviecatchapp.adapter.CollectionAdapter
import com.yusufcansenturk.moviecatchapp.databinding.FragmentMovieListsBinding
import com.yusufcansenturk.moviecatchapp.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"

@AndroidEntryPoint
class MovieListsFragment : Fragment() {

    private lateinit var binding: FragmentMovieListsBinding
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMovieListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreateCollection.setOnClickListener {
            findNavController().navigate(R.id.action_favoriteFragment_to_createCollectionFragment)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            MovieListsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

}