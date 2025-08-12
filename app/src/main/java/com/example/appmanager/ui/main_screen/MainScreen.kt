package com.example.appmanager.ui.main_screen

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.appmanager.domain.model.AppModel
import com.example.appmanager.presentation.MainScreenState
import com.example.appmanager.presentation.MainScreenViewModel
import com.example.appmanager.ui.theme.AppManagerTheme
import com.example.appmanager.ui.theme.AppTypography
import com.example.appmanager.ui.theme.displayFontFamily
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsStateWithLifecycle()

    AppManagerTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            Column {
                Text(
                    "AppManager",
                    style = AppTypography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 4.dp),
                    thickness = 1.dp
                )
                Crossfade(
                    targetState = state,
                    animationSpec = tween(1500)
                ) { currentState ->
                    when (currentState) {
                        is MainScreenState.Content -> {
                            ContentState(((state as MainScreenState.Content).appsData))
                        }

                        is MainScreenState.Loading -> {
                            LoadingState()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContentState(apps: List<AppModel>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(apps) { app ->
            AppCard(appInfo = app)
        }
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                "Загрузка данных...",
                style = AppTypography.headlineSmall,
                fontFamily = displayFontFamily
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun MainScreenPreview() {
    MainScreen()
}