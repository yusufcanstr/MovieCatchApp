package com.yusufcansenturk.moviecatchapp.ui.fragments.favoritePages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yusufcansenturk.moviecatchapp.adapter.CollectionMovieAdapter
import com.yusufcansenturk.moviecatchapp.databinding.FragmentCollectionMoviesBinding
import com.yusufcansenturk.moviecatchapp.util.UiState
import com.yusufcansenturk.moviecatchapp.util.hide
import com.yusufcansenturk.moviecatchapp.util.show
import com.yusufcansenturk.moviecatchapp.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionMoviesFragment : BottomSheetDialogFragment() {

    private lateinit var binding:FragmentCollectionMoviesBinding
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCollectionMoviesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val collectionName = it.getString("collectionName")
            if (collectionName != null) {
                viewModel.loadCollectionData(collectionName)
                binding.collectionName.text = collectionName
            }
        }

        viewModel.collectionData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.progressBar.show()
                    binding.collectionList.hide()
                    binding.imgErrorMessage.hide()
                }
                is UiState.Failure -> {
                    binding.progressBar.hide()
                    binding.collectionList.hide()
                    binding.imgErrorMessage.show()
                }
                is UiState.Success -> {
                    binding.progressBar.hide()
                    binding.collectionList.show()
                    binding.imgErrorMessage.hide()
                    binding.collectionList.apply {
                        adapter = CollectionMovieAdapter(state.data)
                        binding.collectionList.adapter = adapter
                        binding.collectionList.layoutManager = GridLayoutManager(requireContext(),3)
                    }
                }
                else -> {}
            }
        }

    }

}