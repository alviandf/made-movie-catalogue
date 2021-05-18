package com.alviandf.core.data

import com.alviandf.core.data.source.local.LocalDataSource
import com.alviandf.core.data.source.remote.RemoteDataSource
import com.alviandf.core.data.source.remote.network.ApiResponse
import com.alviandf.core.data.source.remote.response.MovieOrTvShowResponse
import com.alviandf.core.domain.model.MovieOrTvShow
import com.alviandf.core.domain.repository.ICatalogueAppRepository
import com.alviandf.core.utils.AppExecutors
import com.alviandf.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatalogueAppRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICatalogueAppRepository {

    override fun getMovies(): Flow<Resource<List<MovieOrTvShow>>> =
        object : NetworkBoundResource<List<MovieOrTvShow>, List<MovieOrTvShowResponse>>() {
            override fun loadFromDB(): Flow<List<MovieOrTvShow>> {
                return localDataSource.getMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieOrTvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieOrTvShowResponse>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieOrTvShowResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMoviesOrTvShows(movieList)
            }
        }.asFlow()

    override fun getTvShows(): Flow<Resource<List<MovieOrTvShow>>> =
        object : NetworkBoundResource<List<MovieOrTvShow>, List<MovieOrTvShowResponse>>() {
            override fun loadFromDB(): Flow<List<MovieOrTvShow>> {
                return localDataSource.getTvShows().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieOrTvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieOrTvShowResponse>>> {
                return remoteDataSource.getTvShows()
            }

            override suspend fun saveCallResult(data: List<MovieOrTvShowResponse>) {
                val tvShowList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMoviesOrTvShows(tvShowList)
            }
        }.asFlow()

    override fun getSearchMovies(search: String): Flow<List<MovieOrTvShow>> {
        return localDataSource.getSearchMovies(search).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getSearchTvShows(search: String): Flow<List<MovieOrTvShow>> {
        return localDataSource.getSearchTvShows(search).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteMovies(): Flow<List<MovieOrTvShow>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteTvShows(): Flow<List<MovieOrTvShow>> {
        return localDataSource.getFavoriteTvShows().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovieOrTvShow(movieOrTvShow: MovieOrTvShow, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movieOrTvShow)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovieOrTvShow(movieEntity, state) }
    }
}