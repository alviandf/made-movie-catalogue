package com.alviandf.mademoviecatalogue.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alviandf.core.domain.model.MovieOrTvShow
import com.alviandf.core.utils.Const
import com.alviandf.mademoviecatalogue.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.imgBackdrop
import kotlinx.android.synthetic.main.activity_detail.ratingBar
import kotlinx.android.synthetic.main.activity_detail.tvDescription
import kotlinx.android.synthetic.main.activity_detail.tvRating
import kotlinx.android.synthetic.main.activity_detail.tvTitle
import kotlinx.android.synthetic.main.activity_detail.tvVoteCount
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var itemFavorite: MenuItem
    private lateinit var detailMovieOrTvShow: MovieOrTvShow
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<MovieOrTvShow>(Const.EXTRA_MOVIE_OR_TVSHOW)

        if (data != null) {
            detailMovieOrTvShow = data
            isFavorite = data.isFavorite
            setUI(data)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            itemFavorite.setIcon(R.drawable.ic_favorite_true)
        } else {
            itemFavorite.setIcon(R.drawable.ic_favorite_false)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUI(data: MovieOrTvShow) {
        Glide.with(this).load(Const.URL_BACKDROP + data.backdrop_path).into(imgBackdrop)
        tvTitle.text = if(data.title.isNotEmpty()) data.title else data.name
        tvDescription.text = data.overview
        tvRating.text = data.vote_average.toString()
        tvVoteCount.text = data.vote_count.toString() + " votes"
        ratingBar.rating = ((5f * (data.vote_average / 10f)).toFloat())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)

        itemFavorite = menu!!.findItem(R.id.action_favorite_detail)
        setStatusFavorite(isFavorite)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite_detail) {
            isFavorite = !isFavorite
            detailViewModel.setFavoriteMovieOrTvShow(detailMovieOrTvShow, isFavorite)
            setStatusFavorite(isFavorite)
        }
        return super.onOptionsItemSelected(item)
    }
}