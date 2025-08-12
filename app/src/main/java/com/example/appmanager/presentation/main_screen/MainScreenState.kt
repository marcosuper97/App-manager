package com.example.appmanager.presentation.main_screen

import com.example.appmanager.domain.model.AppModel

interface MainScreenState {
    data class Content(val appsData: List<AppModel>) : MainScreenState
    data object Loading : MainScreenState
}