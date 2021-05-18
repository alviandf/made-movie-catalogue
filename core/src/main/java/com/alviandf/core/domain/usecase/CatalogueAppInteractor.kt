package com.alviandf.core.domain.usecase

import com.alviandf.core.data.Resource
import com.alviandf.core.domain.model.MovieOrTvShow
import com.alviandf.core.domain.repository.ICatalogueAppRepository
import kotlinx.coroutines.flow.Flow

class CatalogueAppInteractor(private val iCatalogueAppRepository: ICatalogueAppRepository) : CatalogueAppUseCase {

    override fun getMovies(): Flow<Resource<List<MovieOrTvShow>>> =
        iCatalogueAppRepository.getMovies()

    override fun getTvShows(): Flow<Resource<List<MovieOrTvShow>>> =
        iCatalogueAppRepository.getTvShows()

    override fun getFavoriteMovies(): Flow<List<MovieOrTvShow>> =
        iCatalogueAppRepository.getFavoriteMovies()

    override fun getSearchMovies(search: String): Flow<List<MovieOrTvShow>> =
        iCatalogueAppRepository.getSearchMovies(search)

    override fun getSearchTvShows(search: String): Flow<List<MovieOrTvShow>> =
        iCatalogueAppRepository.getSearchTvShows(search)

    override fun getFavoriteTvShows(): Flow<List<MovieOrTvShow>> =
        iCatalogueAppRepository.getFavoriteTvShows()

    override fun setFavoriteMovieOrTvShow(movieOrTvShow: MovieOrTvShow, state: Boolean) =
        iCatalogueAppRepository.setFavoriteMovieOrTvShow(movieOrTvShow, state)

}