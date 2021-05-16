package com.alviandf.core.di

import androidx.room.Room
import com.alviandf.core.data.CatalogueAppRepository
import com.alviandf.core.data.source.local.LocalDataSource
import com.alviandf.core.data.source.local.room.MovieAndTvShowDatabase
import com.alviandf.core.data.source.remote.RemoteDataSource
import com.alviandf.core.data.source.remote.network.ApiService
import com.alviandf.core.domain.repository.ICatalogueAppRepository
import com.alviandf.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieAndTvShowDatabase>().movieAndTvShowDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieAndTvShowDatabase::class.java, "movieAndTvShow.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/discover/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICatalogueAppRepository> { CatalogueAppRepository(get(), get(), get()) }
}