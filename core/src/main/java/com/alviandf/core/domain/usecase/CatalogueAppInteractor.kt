package com.alviandf.core.domain.usecase

import com.alviandf.core.data.Resource
import com.alviandf.core.domain.model.MovieOrTvShow
import com.alviandf.core.domain.repository.ICatalogueAppRepository
import kotlinx.coroutines.flow.Flow

class CatalogueAppInteractor(private val iCatalogueAppRepository: ICatalogueAppRepository) : CatalogueAppUseCase {

    override fun getAllMovies(): Flow<Resource<List<MovieOrTvShow>>> =
        iCatalogueAppRepository.getAllMovies()

    override fun getAllTvShows(): Flow<Resource<List<MovieOrTvShow>>> =
        iCatalogueAppRepository.getAllTvShows()

    override fun getFavoriteMovies(): Flow<List<MovieOrTvShow>> =
        iCatalogueAppRepository.getFavoriteMovies()

    override fun getSearchMovies(search: String): Flow<List<MovieOrTvShow>> =
        iCatalogueAppRepository.getSearchMovies(search)

    override fun getSearchTvShows(search: String): Flow<List<MovieOrTvShow>> =
        iCatalogueAppRepository.getSearchTvShows(search)

    override fun getFavoriteTvShows(): Flow<List<MovieOrTvShow>> =
        iCatalogueAppRepository.getFavoriteTvShows()

    override fun setMovieFavorite(movieOrTvShow: MovieOrTvShow, state: Boolean) =
        iCatalogueAppRepository.setMovieFavorite(movieOrTvShow, state)

}