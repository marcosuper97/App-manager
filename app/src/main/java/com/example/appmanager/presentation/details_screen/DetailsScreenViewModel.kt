package com.example.appmanager.presentation.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmanager.domain.impl.AppDetailsInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val appDetailsInteractor: AppDetailsInteractor,
    private val packageName: String
) : ViewModel() {
    private val _screenState = MutableStateFlow<DetailsScreenState>(DetailsScreenState.Loading)
    val screenState: StateFlow<DetailsScreenState> get() = _screenState

    init {
        viewModelScope.launch {
            val appData = appDetailsInteractor.getDetails(packageName)
            if (appData != null) {
                _screenState.value = DetailsScreenState.Content(appData)
            }
        }
    }

    fun launchApp(){
        viewModelScope.launch {
            appDetailsInteractor.launchApp(packageName)
        }
    }
}