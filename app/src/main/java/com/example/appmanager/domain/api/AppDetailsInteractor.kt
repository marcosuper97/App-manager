package com.example.appmanager.domain.api

import com.example.appmanager.domain.model.AppModel

interface AppDetailsInteractor {
    suspend fun getDetails(name: String): AppModel?
    fun launchApp()
}