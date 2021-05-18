package com.alviandf.mademoviecatalogue.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alviandf.mademoviecatalogue.favorite.di.favoriteModule
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_favorite.tabs
import kotlinx.android.synthetic.main.fragment_favorite.viewPager
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    companion object {
        private val TAB_TITLES = arrayOf(
            "Movie",
            "TV Show"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        initViewPager()
    }

    private fun initViewPager() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
    }
}