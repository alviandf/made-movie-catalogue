package com.alviandf.mademoviecatalogue.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alviandf.core.domain.model.MovieOrTvShow
import com.alviandf.core.ui.MoviesOrTvShowsAdapter
import com.alviandf.core.utils.Const
import com.alviandf.mademoviecatalogue.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_movie_or_tvshow.icNoData
import kotlinx.android.synthetic.main.fragment_movie_or_tvshow.rvMoviesOrTvShows
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesOrTvShowsFragment : Fragment() {

    private var index: Int? = 0

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private val moviesOrTvShowsAdapter: MoviesOrTvShowsAdapter by lazy {
        MoviesOrTvShowsAdapter()
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int) =
            MoviesOrTvShowsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_or_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        index = arguments?.getInt(ARG_SECTION_NUMBER, 0)

        initViewModel()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        moviesOrTvShowsAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(Const.EXTRA_MOVIE_OR_TVSHOW, selectedData)
            startActivity(intent)
        }

        rvMoviesOrTvShows.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moviesOrTvShowsAdapter
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }

    private fun initViewModel() {
        if (index == 1) {
            favoriteViewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
                setUI(movies)
            })
        } else if (index == 2) {
            favoriteViewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { tvShows ->
                setUI(tvShows)
            })
        }
    }

    private fun setUI(data: List<MovieOrTvShow>) {
        if (data.isEmpty()) {
            icNoData.visibility = View.VISIBLE
        } else {
            icNoData.visibility = View.GONE
        }
        moviesOrTvShowsAdapter.setData(data)
    }
}