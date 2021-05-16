package com.alviandf.core.utils

import com.alviandf.core.data.source.local.entity.MovieOrTvShowEntity
import com.alviandf.core.data.source.remote.response.MovieOrTvShowResponse
import com.alviandf.core.domain.model.MovieOrTvShow

object DataMapper {

    fun mapMovieResponsesToEntities(input: List<MovieOrTvShowResponse>): List<MovieOrTvShowEntity> {
        val movieList = ArrayList<MovieOrTvShowEntity>()
        input.map {
            val movie = MovieOrTvShowEntity(
                type = it.type ?: "",
                backdrop_path = it.backdrop_path ?: "",
                id = it.id ?: 0,
                original_language = it.original_language ?: "",
                name = it.name?: "",
                overview = it.overview?: "",
                popularity = it.popularity?: 0.0,
                poster_path = it.poster_path?: "",
                title = it.title?: "",
                video = it.video?: false,
                vote_average = it.vote_average ?: 0.0,
                vote_count = it.vote_count?: 0,
                isFavorite = it.isFavorite ?: false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieOrTvShowEntity>): List<MovieOrTvShow> {
        return input.map {
            MovieOrTvShow(
                type = it.type,
                backdrop_path = it.backdrop_path,
                id = it.id,
                original_language = it.original_language,
                name = it.name,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                title = it.title,
                video = it.video,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                isFavorite = it.isFavorite
            )
        }
    }

    fun mapDomainToEntity(it: MovieOrTvShow): MovieOrTvShowEntity {
        return MovieOrTvShowEntity(
            type = it.type,
            backdrop_path = it.backdrop_path,
            id = it.id,
            original_language = it.original_language,
            name = it.name,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            title = it.title,
            video = it.video,
            vote_average = it.vote_average,
            vote_count = it.vote_count,
            isFavorite = it.isFavorite
        )
    }
}