package com.alviandf.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListMovieOrTvShowResponse(
    val page: Int?,
    val results: List<MovieOrTvShowResponse>,
    val total_pages: Int?,
    val total_results: Int?
): Parcelable