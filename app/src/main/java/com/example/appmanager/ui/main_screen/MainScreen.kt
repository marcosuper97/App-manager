package com.example.appmanager.ui.main_screen

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.appmanager.R
import com.example.appmanager.domain.model.AppPreviewModel
import com.example.appmanager.presentation.main_screen.MainScreenState
import com.example.appmanager.presentation.main_screen.MainScreenViewModel
import com.example.appmanager.ui.components.LoadingState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private const val ANIM_DURATION = 1500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = koinViewModel()
) {
    val scope = rememberCoroutineScope()
    val onCardClick: (String) -> Unit = { name ->
        scope.launch {
            navController.navigate("detailsScreen/$name")
        }
    }
    val state by viewModel.screenState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Column {
                    HorizontalDivider(
                        modifier = Modifier.padding(bottom = 4.dp),
                        thickness = 1.dp
                    )
                    Crossfade(
                        targetState = state,
                        animationSpec = tween(ANIM_DURATION)
                    ) { currentState ->
                        when (currentState) {
                            is MainScreenState.Content -> {
                                ContentState(
                                    ((state as MainScreenState.Content).appsData),
                                    onCardClick
                                )
                            }

                            is MainScreenState.Loading -> {
                                LoadingState()
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ContentState(
    apps: List<AppPreviewModel>,
    onCardClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(apps) { app ->
            AppCard(
                appInfo = app,
                modifier = Modifier.clickable(
                    onClick = {
                        onCardClick(app.name)
                    }
                )
            )
        }
    }
}