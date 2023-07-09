package com.yusufcansenturk.moviecatchapp.ui.fragments.favoritePages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.yusufcansenturk.moviecatchapp.adapter.WatchListAdapter
import com.yusufcansenturk.moviecatchapp.databinding.FragmentWatchListBinding
import com.yusufcansenturk.moviecatchapp.util.FavoriteClickType
import com.yusufcansenturk.moviecatchapp.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"

@AndroidEntryPoint
class WatchListFragment : Fragment() {

    private lateinit var binding: FragmentWatchListBinding
    private val viewModel: FavoriteViewModel by viewModels()
    //private lateinit var adapter : WatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadWatchData()
        viewModel.watchData.observe(viewLifecycleOwner) { watchData ->
            watchData?.let {
                binding.watchList.apply {
                    adapter = WatchListAdapter(watchData) { _, type ->
                        when(type) {
                            FavoriteClickType.POSTER -> {

                            }
                            else -> {}
                        }
                    }
                    binding.watchList.adapter = adapter
                    binding.watchList.layoutManager = GridLayoutManager(requireContext(), 3)
                }
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            WatchListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

}