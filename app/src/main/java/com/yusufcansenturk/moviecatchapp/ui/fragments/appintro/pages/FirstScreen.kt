package com.yusufcansenturk.moviecatchapp.ui.fragments.appintro.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2
import com.yusufcansenturk.moviecatchapp.R
import com.yusufcansenturk.moviecatchapp.databinding.FragmentFirstScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstScreen : Fragment() {

    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onResume() {
        super.onResume()
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        val prevButton = activity?.findViewById<RelativeLayout>(R.id.prevButton)
        val nextButton = activity?.findViewById<RelativeLayout>(R.id.nxtButton)

        prevButton?.alpha = 0f
        prevButton?.isClickable = false

        nextButton?.setOnClickListener {
            viewPager?.currentItem = 1
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}