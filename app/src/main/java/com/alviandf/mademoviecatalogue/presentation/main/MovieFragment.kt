package com.alviandf.mademoviecatalogue.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alviandf.core.domain.model.MovieOrTvShow
import com.alviandf.core.ui.MoviesOrTvShowsAdapter
import com.alviandf.core.utils.Const
import com.alviandf.mademoviecatalogue.R
import com.alviandf.mademoviecatalogue.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_movie_or_tvshow.icNoData
import kotlinx.android.synthetic.main.fragment_movie_or_tvshow.rvMoviesOrTvShows
import kotlinx.android.synthetic.main.fragment_movie_or_tvshow.searchView
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val movieOrTvShowAdapter: MoviesOrTvShowsAdapter by lazy {
        MoviesOrTvShowsAdapter()
    }
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_or_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getMovies()
        initSearch()
    }

    private fun initSearch() {
        searchView.setOnQueryTextListener(object : OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                mainViewModel.getSearchMovies(newText).observe(viewLifecycleOwner){
                    setUI(it)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })
    }

    fun setUI(data: List<MovieOrTvShow>){
        if(data.isEmpty()){
            icNoData.visibility = View.VISIBLE
        } else {
            icNoData.visibility = View.GONE
        }
        movieOrTvShowAdapter.setData(data)
    }


    private fun getMovies() {
        mainViewModel.getMovies().observe(viewLifecycleOwner, {
            val data = it.data
            if (data != null) {
                setUI(data)
            }
        })
    }

    private fun initRecyclerView() {
        movieOrTvShowAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(Const.EXTRA_MOVIE_OR_TVSHOW, selectedData)
            startActivity(intent)
        }

        rvMoviesOrTvShows.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieOrTvShowAdapter
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }
}