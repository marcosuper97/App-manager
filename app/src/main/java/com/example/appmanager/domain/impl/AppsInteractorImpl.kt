package com.example.appmanager.domain.impl

import com.example.appmanager.domain.api.AppsInteractor
import com.example.appmanager.domain.api.AppsRepository
import com.example.appmanager.domain.model.AppModel
import kotlinx.coroutines.flow.Flow

class AppsInteractorImpl(
    private val appsRepository: AppsRepository
) : AppsInteractor {
    override fun fetchData(): Flow<List<AppModel>> = appsRepository.getInstalledApplications()
}