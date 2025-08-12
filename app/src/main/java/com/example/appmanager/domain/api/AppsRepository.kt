package com.example.appmanager.domain.api

import com.example.appmanager.domain.model.AppModel
import kotlinx.coroutines.flow.Flow

interface AppsRepository {
    fun getInstalledApplications(): Flow<List<AppModel>>
}