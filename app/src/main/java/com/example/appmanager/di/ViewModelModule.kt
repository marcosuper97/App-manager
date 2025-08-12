package com.example.appmanager.di

import com.example.appmanager.presentation.MainScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainScreenViewModel(get())
    }
}