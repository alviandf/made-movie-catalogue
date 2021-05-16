package com.alviandf.mademoviecatalogue.favorite.di

import com.alviandf.mademoviecatalogue.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel {
        FavoriteViewModel(get())
    }
}