package com.example.appmanager.domain.impl

import android.util.Log
import com.example.appmanager.domain.api.AppDetailsInteractor
import com.example.appmanager.domain.api.AppLauncherRepository
import com.example.appmanager.domain.api.AppsRepository
import com.example.appmanager.domain.model.AppModel

class AppDetailsInteractorImpl(
    private val appsRepository: AppsRepository,
    private val appLauncherRepository: AppLauncherRepository
) : AppDetailsInteractor {
    override suspend fun getDetails(name: String): AppModel? {
        val app = appsRepository.getAppDetails(name)
        return app
    }

    override fun launchApp(packageName: String) {
        appLauncherRepository.launchApp(packageName)
    }
}