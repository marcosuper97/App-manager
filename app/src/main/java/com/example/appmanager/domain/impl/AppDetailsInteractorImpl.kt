package com.example.appmanager.domain.impl

import com.example.appmanager.domain.api.AppDetailsInteractor
import com.example.appmanager.domain.api.AppLauncherRepository
import com.example.appmanager.domain.api.AppsRepository
import com.example.appmanager.domain.model.AppModel
import kotlinx.coroutines.flow.first

class AppDetailsInteractorImpl(
    private val appsRepository: AppsRepository,
    private val appLauncherRepository: AppLauncherRepository
) : AppDetailsInteractor {
    override suspend fun getDetails(packageName: String): AppModel? {
        val appsList = appsRepository.getInstalledApplications().first()
        val app = appsList.find { it.packageName == packageName }
        return app
    }

    override fun launchApp(packageName: String) {
        appLauncherRepository.launchApp(packageName)
    }
}