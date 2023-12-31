package com.yusufcansenturk.moviecatchapp.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yusufcansenturk.moviecatchapp.R
import com.yusufcansenturk.moviecatchapp.databinding.FragmentMainBinding
import com.yusufcansenturk.moviecatchapp.prefs.MovieSesionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var movieSesionManager: MovieSesionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        if (movieSesionManager.getIsFirstRun()) {
            movieSesionManager.setIsFirstRun(false)
        }

        setupTabBar()

        return view
    }

    private fun setupTabBar() {
        binding.bottomNavBar.setItemSelected(R.id.nav_home,true)
        binding.bottomNavBar.setOnItemSelectedListener {
            when(it) {
                R.id.nav_home -> childFragmentManager.primaryNavigationFragment?.findNavController()?.navigate(R.id.homeFragment)
                R.id.nav_favorites -> childFragmentManager.primaryNavigationFragment?.findNavController()?.navigate(R.id.favoriteFragment)
                R.id.nav_settings -> childFragmentManager.primaryNavigationFragment?.findNavController()?.navigate(R.id.settingsFragment)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}