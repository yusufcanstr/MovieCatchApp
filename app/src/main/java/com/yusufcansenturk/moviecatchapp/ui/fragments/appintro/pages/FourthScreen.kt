package com.yusufcansenturk.moviecatchapp.ui.fragments.appintro.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2
import com.yusufcansenturk.moviecatchapp.R
import com.yusufcansenturk.moviecatchapp.databinding.FragmentFourthScreenBinding
import com.yusufcansenturk.moviecatchapp.databinding.FragmentThirdScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FourthScreen : Fragment() {
    private var _binding: FragmentFourthScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFourthScreenBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }

    override fun onResume() {
        super.onResume()
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        val prevButton = activity?.findViewById<RelativeLayout>(R.id.prevButton)
        val nextButton = activity?.findViewById<RelativeLayout>(R.id.nxtButton)

        nextButton?.alpha = 1f
        nextButton?.isClickable = true

        prevButton?.setOnClickListener {
            viewPager?.currentItem = 2
        }

        nextButton?.setOnClickListener {
            viewPager?.currentItem = 4
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}