package com.alviandf.mademoviecatalogue.di

import com.alviandf.core.domain.usecase.CatalogueAppInteractor
import com.alviandf.core.domain.usecase.CatalogueAppUseCase
import com.alviandf.mademoviecatalogue.presentation.detail.DetailViewModel
import com.alviandf.mademoviecatalogue.presentation.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CatalogueAppUseCase> { CatalogueAppInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}