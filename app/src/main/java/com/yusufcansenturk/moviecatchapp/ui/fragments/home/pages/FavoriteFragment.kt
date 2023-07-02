package com.yusufcansenturk.moviecatchapp.ui.fragments.home.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yusufcansenturk.moviecatchapp.R
import com.yusufcansenturk.moviecatchapp.adapter.FavoritePagerAdapter
import com.yusufcansenturk.moviecatchapp.databinding.FragmentFavoriteBinding
import com.yusufcansenturk.moviecatchapp.util.FavoriteTabs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager2.adapter = FavoritePagerAdapter(this)
        viewPager2SetupWithTabLayout(
            tabLayout = binding.tabLayout,
            viewPager2 = binding.viewPager2
        )
        binding.tabLayout.onTabSelectionListener()

    }

    private fun viewPager2SetupWithTabLayout(tabLayout: TabLayout, viewPager2: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.item_tab_layout, null)
            val textViewTitle: TextView = view.findViewById<TextView>(R.id.tabTitle)
            when (position) {
                FavoriteTabs.FAVORITE.index -> {
                    tab.customView = view
                    textViewTitle.text = getString(R.string.favori_list_frg)
                    tab.onSelection(true)
                }
                FavoriteTabs.WATCHLIST.index -> {
                    tab.customView = view
                    textViewTitle.text = getString(R.string.watch_list_frg)
                    tab.onSelection(false)
                }
                FavoriteTabs.MOVIE_LISTS.index -> {
                    tab.customView = view
                    textViewTitle.text = getString(R.string.movie_lists_frg)
                    tab.onSelection(false)
                }
            }
        }.attach()
        }
    }

inline fun TabLayout.onTabSelectionListener(
    crossinline onTabSelected: (TabLayout.Tab?) -> Unit = {},
    crossinline onTabUnselected: (TabLayout.Tab?) -> Unit? = {},
) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.onSelection(true)
            onTabSelected.invoke(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            tab?.onSelection(false)
            onTabUnselected.invoke(tab)
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}

fun TabLayout.Tab.onSelection(isSelected: Boolean = true) {
    val textViewTitle: TextView? = customView?.findViewById<TextView>(R.id.tabTitle)
    textViewTitle?.let { textView ->
        textView.setTextColor(
            ContextCompat.getColor(
                textView.context,
                if (isSelected) R.color.tab_selected else R.color.tab_unselected
            )
        )
    }
}