package com.alviandf.mademoviecatalogue.presentation.detail

import androidx.lifecycle.ViewModel
import com.alviandf.core.domain.model.MovieOrTvShow
import com.alviandf.core.domain.usecase.CatalogueAppUseCase

class DetailViewModel(private val catalogueAppUseCase: CatalogueAppUseCase) : ViewModel() {

    fun setFavoriteMovieOrTvShow(movieOrTvShow: MovieOrTvShow, newStatus: Boolean) {
        catalogueAppUseCase.setMovieFavorite(movieOrTvShow, newStatus)
    }
}
