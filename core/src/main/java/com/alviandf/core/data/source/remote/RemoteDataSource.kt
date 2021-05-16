package com.alviandf.core.data.source.remote

import com.alviandf.core.data.source.remote.network.ApiResponse
import com.alviandf.core.data.source.remote.network.ApiService
import com.alviandf.core.data.source.remote.response.MovieOrTvShowResponse
import com.alviandf.core.utils.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovies(): Flow<ApiResponse<List<MovieOrTvShowResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    movieList.map {
                        it.type = Const.TYPE_MOVIE
                    }
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShows(): Flow<ApiResponse<List<MovieOrTvShowResponse>>> {
        return flow {
            try {
                val response = apiService.getTvShows()
                val tvShowList = response.results
                if (tvShowList.isNotEmpty()) {
                    tvShowList.map {
                        it.type = Const.TYPE_TVSHOW
                    }
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}
