package com.yusufcansenturk.moviecatchapp.ui.fragments.appintro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yusufcansenturk.moviecatchapp.adapter.ViewPagerAdapter
import com.yusufcansenturk.moviecatchapp.databinding.FragmentAppIntroBinding
import com.yusufcansenturk.moviecatchapp.ui.fragments.appintro.pages.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppIntroFragment : Fragment() {
    private var _binding: FragmentAppIntroBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAppIntroBinding.inflate(inflater, container, false)
        val view = binding.root

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen(),
            FourthScreen(),
            FifthScreen()
        )

        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}