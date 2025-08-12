package com.example.appmanager.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmanager.domain.api.AppsInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val appsInteractor: AppsInteractor
) : ViewModel() {
    private val _screenState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val screenState: StateFlow<MainScreenState> get() = _screenState

    init {
        viewModelScope.launch {
            appsInteractor.fetchData().collect { data ->
                _screenState.value = MainScreenState.Content(data)
            }
        }
    }
}