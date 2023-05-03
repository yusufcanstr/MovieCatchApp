package com.yusufcansenturk.moviecatchapp.ui.fragments.appintro.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2
import com.yusufcansenturk.moviecatchapp.R
import com.yusufcansenturk.moviecatchapp.databinding.FragmentFifthScreenBinding
import com.yusufcansenturk.moviecatchapp.databinding.FragmentFourthScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FifthScreen : Fragment() {
    private var _binding: FragmentFifthScreenBinding? = null
    private val binding get() = _binding!!

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

        binding.nextButton.setOnClickListener {

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