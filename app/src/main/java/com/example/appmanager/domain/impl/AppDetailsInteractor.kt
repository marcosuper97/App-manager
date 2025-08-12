package com.example.appmanager.domain.impl

import com.example.appmanager.domain.api.AppDetailsInteractor
import com.example.appmanager.domain.api.AppLauncherRepository
import com.example.appmanager.domain.api.AppsRepository
import com.example.appmanager.domain.model.AppModel
import kotlinx.coroutines.flow.first

class AppDetailsInteractor(
    private val appsRepository: AppsRepository,
    private val appLauncherRepository: AppLauncherRepository
) : AppDetailsInteractor {
    override suspend fun getDetails(packageName: String): AppModel? {
        val appsList = appsRepository.getInstalledApplications().first()
        return appsList.find { it.name == packageName }
    }

    override fun launchApp(packageName: String) {
        appLauncherRepository.launchApp(packageName)
    }
}