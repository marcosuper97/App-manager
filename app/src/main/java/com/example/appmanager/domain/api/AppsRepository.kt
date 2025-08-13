package com.example.appmanager.domain.api

import android.content.pm.ApplicationInfo
import com.example.appmanager.domain.model.AppModel
import com.example.appmanager.domain.model.AppPreviewModel
import kotlinx.coroutines.flow.Flow

interface AppsRepository {
    val installedApplications: List<ApplicationInfo>
    fun getInstalledApplications(): Flow<List<AppPreviewModel>>
    fun getAppDetails(name: String): AppModel?
}