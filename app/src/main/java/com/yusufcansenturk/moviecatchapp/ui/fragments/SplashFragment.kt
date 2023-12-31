package com.yusufcansenturk.moviecatchapp.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yusufcansenturk.moviecatchapp.R
import com.yusufcansenturk.moviecatchapp.databinding.FragmentSplashBinding
import com.yusufcansenturk.moviecatchapp.prefs.MovieSesionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var movieSesionManager: MovieSesionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root

        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (movieSesionManager.getIsFirstRun()) {
                    findNavController().navigate(R.id.action_splashFragment_to_appIntroFragment)
                }else {
                    findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                }
            },
            3000)


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}