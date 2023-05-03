package com.yusufcansenturk.moviecatchapp.ui.fragments.appintro.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.yusufcansenturk.moviecatchapp.R
import com.yusufcansenturk.moviecatchapp.databinding.FragmentFifthScreenBinding
import com.yusufcansenturk.moviecatchapp.databinding.FragmentFourthScreenBinding
import com.yusufcansenturk.moviecatchapp.di.dao.GenreData
import com.yusufcansenturk.moviecatchapp.util.StringHelper
import com.yusufcansenturk.moviecatchapp.viewmodel.GenreViewModel
import com.yusufcansenturk.moviecatchapp.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FifthScreen : Fragment() {
    private var _binding: FragmentFifthScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var genreViewModel: GenreViewModel
    private lateinit var homePageViewModel: HomePageViewModel

    private var stringHelper: StringHelper? = null
    private var genreList : MutableList<GenreData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFifthScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        stringHelper = StringHelper()

        genreList = mutableListOf()

        genreViewModel = ViewModelProvider(this).get(GenreViewModel::class.java)
        homePageViewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)
        homePageViewModel.getObserveGenre().observe(viewLifecycleOwner,{ genre ->
            if (genre != null) {
                for (item in genre.genres){

                    val tr_name = stringHelper!!.getTrName(item.name)
                    val genre = GenreData(0 , item.id, item.name,tr_name)
                    genreList!!.add(genre)

                }

                genreViewModel.addAllGenres(genreList!!)
                findNavController().navigate(R.id.action_appIntroFragment_to_mainFragment)
            }
        })

        binding.nextButton.setOnClickListener {
            homePageViewModel.loadGenreData()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        val prevButton = activity?.findViewById<RelativeLayout>(R.id.prevButton)
        val nextButton = activity?.findViewById<RelativeLayout>(R.id.nxtButton)

        prevButton?.setOnClickListener {
            viewPager?.currentItem = 3
        }

        nextButton?.alpha = 0f
        nextButton?.isClickable = false



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}