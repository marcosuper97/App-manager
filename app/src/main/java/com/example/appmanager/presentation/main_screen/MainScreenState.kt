package com.example.appmanager.presentation.main_screen

import com.example.appmanager.domain.model.AppPreviewModel

interface MainScreenState {
    data class Content(val appsData: List<AppPreviewModel>) : MainScreenState
    data object Loading : MainScreenState
}