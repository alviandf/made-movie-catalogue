package com.alviandf.core.data.source.remote.network

import com.alviandf.core.data.source.remote.response.ListMovieOrTvShowResponse
import com.alviandf.core.utils.Const
import retrofit2.http.GET

interface ApiService {

    @GET("movie?api_key=${Const.API_KEY}")
    suspend fun getMovies(): ListMovieOrTvShowResponse

    @GET("tv?api_key=${Const.API_KEY}")
    suspend fun getTvShows(): ListMovieOrTvShowResponse
}