package com.alviandf.mademoviecatalogue.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alviandf.core.data.Resource
import com.alviandf.core.domain.model.MovieOrTvShow
import com.alviandf.core.domain.usecase.CatalogueAppUseCase

class MainViewModel(private val catalogueAppUseCase: CatalogueAppUseCase) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<MovieOrTvShow>>> {
        return catalogueAppUseCase.getMovies().asLiveData()
    }

    fun getTvShows(): LiveData<Resource<List<MovieOrTvShow>>> {
        return catalogueAppUseCase.getTvShows().asLiveData()
    }

    fun getSearchMovies(search: String): LiveData<List<MovieOrTvShow>> {
        return catalogueAppUseCase.getSearchMovies(search).asLiveData()
    }

    fun getSearchTvShows(search: String): LiveData<List<MovieOrTvShow>> {
        return catalogueAppUseCase.getSearchTvShows(search).asLiveData()
    }
}