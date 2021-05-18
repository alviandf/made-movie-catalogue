package com.alviandf.core.data.source.local

import com.alviandf.core.data.source.local.entity.MovieOrTvShowEntity
import com.alviandf.core.data.source.local.room.MovieAndTvShowDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(private val movieAndTvShowDao: MovieAndTvShowDao) {

    fun getMovies(): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getMovies()
    }

    fun getTvShows(): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getTvShows()
    }

    fun getFavoriteMovies(): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getFavoriteMovies()
    }

    fun getFavoriteTvShows(): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getFavoriteTvShows()
    }

    fun getSearchMovies(search: String): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getSearchMovies(search)
            .flowOn(Dispatchers.Default)
            .conflate()
    }

    fun getSearchTvShows(search: String): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getSearchTvShows(search)
            .flowOn(Dispatchers.Default)
            .conflate()
    }

    suspend fun insertMoviesOrTvShows(moviesOrTvShows: List<MovieOrTvShowEntity>) = movieAndTvShowDao.insertMoviesOrTvShows(moviesOrTvShows)

    fun setFavoriteMovieOrTvShow(movieOrTvShow: MovieOrTvShowEntity, newState: Boolean) {
        movieOrTvShow.isFavorite = newState
        movieAndTvShowDao.updateFavoriteMovieOrTvShow(movieOrTvShow)
    }
}