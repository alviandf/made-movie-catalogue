package com.alviandf.core.domain.repository

import com.alviandf.core.data.Resource
import com.alviandf.core.domain.model.MovieOrTvShow
import kotlinx.coroutines.flow.Flow

interface ICatalogueAppRepository {

    fun getMovies(): Flow<Resource<List<MovieOrTvShow>>>

    fun getTvShows(): Flow<Resource<List<MovieOrTvShow>>>

    fun getFavoriteMovies(): Flow<List<MovieOrTvShow>>

    fun getFavoriteTvShows(): Flow<List<MovieOrTvShow>>

    fun getSearchMovies(search: String): Flow<List<MovieOrTvShow>>

    fun getSearchTvShows(search: String): Flow<List<MovieOrTvShow>>

    fun setFavoriteMovieOrTvShow(movieOrTvShow: MovieOrTvShow, state: Boolean)

}