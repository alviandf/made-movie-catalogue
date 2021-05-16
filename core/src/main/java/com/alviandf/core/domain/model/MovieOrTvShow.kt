package com.alviandf.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieOrTvShow(
    val id: Int,
    var type : String,
    val backdrop_path: String,
    val original_language: String,
    val name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    var isFavorite: Boolean = false,
) : Parcelable