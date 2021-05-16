package com.alviandf.mademoviecatalogue.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alviandf.core.domain.model.MovieOrTvShow
import com.alviandf.core.domain.usecase.CatalogueAppUseCase

class FavoriteViewModel(private val catalogueAppUseCase: CatalogueAppUseCase) : ViewModel() {

    fun getFavoriteMovies(): LiveData<List<MovieOrTvShow>> =
        catalogueAppUseCase.getFavoriteMovies().asLiveData()

    fun getFavoriteTvShows(): LiveData<List<MovieOrTvShow>> =
        catalogueAppUseCase.getFavoriteTvShows().asLiveData()
}

