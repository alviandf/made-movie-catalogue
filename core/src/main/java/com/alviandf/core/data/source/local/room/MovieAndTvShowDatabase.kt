package com.alviandf.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alviandf.core.data.source.local.entity.MovieOrTvShowEntity

@Database(entities = [MovieOrTvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieAndTvShowDatabase : RoomDatabase() {

    abstract fun movieAndTvShowDao(): MovieAndTvShowDao

}