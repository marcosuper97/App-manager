package com.example.appmanager.di

import android.content.pm.PackageManager
import com.example.appmanager.data.impl.AppsRepositoryImpl
import com.example.appmanager.domain.api.AppsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<PackageManager> {
        androidContext().packageManager
    }

    single<AppsRepository> {
        AppsRepositoryImpl(get(), androidContext())
    }
}