package com.example.appmanager.di

import com.example.appmanager.data.impl.AppsRepositoryImpl
import com.example.appmanager.domain.api.AppsInteractor
import com.example.appmanager.domain.impl.AppsInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    factory<AppsInteractor>{
        AppsInteractorImpl(get())
    }
}