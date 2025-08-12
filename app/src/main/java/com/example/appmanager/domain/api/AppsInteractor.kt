package com.example.appmanager.domain.api

import com.example.appmanager.domain.model.AppModel
import kotlinx.coroutines.flow.Flow

interface AppsInteractor {
    fun fetchData(): Flow<List<AppModel>>
}