package com.example.appmanager.di

import com.example.appmanager.presentation.details_screen.DetailsScreenViewModel
import com.example.appmanager.presentation.main_screen.MainScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainScreenViewModel(get())
    }

    viewModel { (packageName: String) ->
        DetailsScreenViewModel(get(), packageName)
    }
}