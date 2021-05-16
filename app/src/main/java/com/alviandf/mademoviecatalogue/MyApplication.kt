package com.alviandf.mademoviecatalogue

import android.app.Application
import com.alviandf.core.di.databaseModule
import com.alviandf.core.di.networkModule
import com.alviandf.core.di.repositoryModule
import com.alviandf.mademoviecatalogue.di.useCaseModule
import com.alviandf.mademoviecatalogue.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@FlowPreview
@ExperimentalCoroutinesApi
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}