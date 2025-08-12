package com.example.appmanager.presentation.details_screen

import com.example.appmanager.domain.model.AppModel

interface DetailsScreenState {
    data class Content(val appData: AppModel) : DetailsScreenState
    data object Loading : DetailsScreenState
    data object Error : DetailsScreenState
}