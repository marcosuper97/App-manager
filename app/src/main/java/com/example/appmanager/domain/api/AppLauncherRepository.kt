package com.example.appmanager.domain.api

interface AppLauncherRepository {
    fun launchApp(packageName: String)
}