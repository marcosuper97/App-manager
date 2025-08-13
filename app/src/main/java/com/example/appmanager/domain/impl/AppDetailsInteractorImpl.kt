package com.example.appmanager.domain.impl

import com.example.appmanager.domain.api.AppDetailsInteractor
import com.example.appmanager.domain.api.AppLauncherRepository
import com.example.appmanager.domain.api.AppsRepository
import com.example.appmanager.domain.model.AppModel

class AppDetailsInteractorImpl(
    private val appsRepository: AppsRepository,
    private val appLauncherRepository: AppLauncherRepository
) : AppDetailsInteractor {
    private var appDetails: AppModel? = null
    override suspend fun getDetails(name: String): AppModel? {
        appDetails = appsRepository.getAppDetails(name)
        return appDetails
    }

    override fun launchApp() {
        appDetails?.let { appLauncherRepository.launchApp(appDetails!!.packageName) }
    }
}