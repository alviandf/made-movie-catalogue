package com.alviandf.core.data.source.local

import com.alviandf.core.data.source.local.entity.MovieOrTvShowEntity
import com.alviandf.core.data.source.local.room.MovieAndTvShowDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(private val movieAndTvShowDao: MovieAndTvShowDao) {

    fun getAllMovies(): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getMovies()
    }

    fun getAllTvShows(): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getTvShows()
    }

    fun getAllFavoriteMovies(): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getFavoriteMovies()
    }

    fun getAllFavoriteTvShows(): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getFavoriteTvShows()
    }

    fun getMovieSearch(search: String): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getSearchMovies(search)
            .flowOn(Dispatchers.Default)
            .conflate()
    }

    fun getTvShowSearch(search: String): Flow<List<MovieOrTvShowEntity>> {
        return movieAndTvShowDao.getSearchTvShows(search)
            .flowOn(Dispatchers.Default)
            .conflate()
    }

    suspend fun insertMovies(moviesOrTvShows: List<MovieOrTvShowEntity>) = movieAndTvShowDao.insertMovie(moviesOrTvShows)

    fun setMovieFavorite(movieOrTvShow: MovieOrTvShowEntity, newState: Boolean) {
        movieOrTvShow.isFavorite = newState
        movieAndTvShowDao.updateFavoriteMovie(movieOrTvShow)
    }
}