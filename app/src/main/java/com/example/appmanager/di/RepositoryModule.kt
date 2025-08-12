package com.example.appmanager.di

import com.example.appmanager.data.impl.AppsRepositoryImpl
import com.example.appmanager.domain.api.AppsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AppsRepository>{
        AppsRepositoryImpl(get(),get())
    }
}