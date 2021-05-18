package com.alviandf.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alviandf.core.data.source.local.entity.MovieOrTvShowEntity
import com.alviandf.core.utils.Const
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieAndTvShowDao {

    @Query("SELECT * FROM ${Const.TB_NAME} WHERE type = 'type_movie'")
    fun getMovies(): Flow<List<MovieOrTvShowEntity>>

    @Query("SELECT * FROM ${Const.TB_NAME} WHERE type = 'type_tvshow'")
    fun getTvShows(): Flow<List<MovieOrTvShowEntity>>

    @Query("SELECT * FROM ${Const.TB_NAME} WHERE type = 'type_movie' AND title LIKE '%' || :search || '%'")
    fun getSearchMovies(search: String): Flow<List<MovieOrTvShowEntity>>

    @Query("SELECT * FROM ${Const.TB_NAME} WHERE type = 'type_tvshow' AND name LIKE '%' || :search || '%'")
    fun getSearchTvShows(search: String): Flow<List<MovieOrTvShowEntity>>

    @Query("SELECT * FROM ${Const.TB_NAME} WHERE type = 'type_movie' AND isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieOrTvShowEntity>>

    @Query("SELECT * FROM ${Const.TB_NAME} WHERE type = 'type_tvshow' AND isFavorite = 1")
    fun getFavoriteTvShows(): Flow<List<MovieOrTvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesOrTvShows(moviesOrTvShows: List<MovieOrTvShowEntity>)

    @Update
    fun updateFavoriteMovieOrTvShow(movieOrTvShow: MovieOrTvShowEntity)
}