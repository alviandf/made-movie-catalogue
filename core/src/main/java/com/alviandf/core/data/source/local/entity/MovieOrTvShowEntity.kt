package com.alviandf.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alviandf.core.utils.Const
import kotlinx.android.parcel.Parcelize

@Entity(tableName = Const.TB_NAME)
@Parcelize
data class MovieOrTvShowEntity(

    @PrimaryKey
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

): Parcelable

//fun MovieOrTvShowResult.toMovieEntity() = MovieEntity(
//    type = type,
//    backdrop_path = backdrop_path,
//    id= id,
//    original_language = original_language,
//    name = name,
//    overview = overview,
//    popularity = popularity,
//    poster_path = poster_path,
//    title = title,
//    video = video,
//    vote_average = vote_average,
//    vote_count = vote_count
//)