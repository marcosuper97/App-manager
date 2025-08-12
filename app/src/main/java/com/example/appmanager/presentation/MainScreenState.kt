package com.example.appmanager.presentation

import com.example.appmanager.domain.model.AppModel

interface MainScreenState {
    data class Content(val appsData: List<AppModel>) : MainScreenState
    data object IsEmpty: MainScreenState
    data object Loading : MainScreenState
    data object Error : MainScreenState
}