package com.example.appmanager.ui.app_details

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.appmanager.R
import com.example.appmanager.domain.model.AppModel
import com.example.appmanager.presentation.details_screen.DetailsScreenState
import com.example.appmanager.presentation.details_screen.DetailsScreenViewModel
import com.example.appmanager.ui.components.LoadingState
import com.example.appmanager.ui.theme.AppTypography
import com.example.appmanager.util.toBitmap
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(
    name: String?,
    navController: NavController,
    viewModel: DetailsScreenViewModel = koinViewModel {
        parametersOf(name)
    }
) {
    val scope = rememberCoroutineScope()
    val state by viewModel.screenState.collectAsStateWithLifecycle()
    val onButtonClick: () -> Unit = {
        scope.launch {
            viewModel.launchApp()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_details)) },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                        modifier = Modifier.clickable(
                            true,
                            onClick = { navController.popBackStack() })
                    )
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Crossfade(
                    targetState = state,
                    animationSpec = tween(1500)
                ) { currentState ->
                    when (currentState) {
                        is DetailsScreenState.Content -> {
                            AppDetails(currentState.appData, onButtonClick)
                        }

                        is DetailsScreenState.Loading -> {
                            LoadingState()
                        }
                    }
                }
            }
        })
}

@Composable
fun AppDetails(appInfo: AppModel, onButtonClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val modifierRow = Modifier
        .fillMaxWidth()
        .padding(vertical = 14.dp, horizontal = 32.dp)
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = BitmapPainter(appInfo.icon.toBitmap().asImageBitmap()),
                contentDescription = "Иконка приложения ${appInfo.name}",
                modifier = Modifier
                    .size(104.dp)
                    .clip(RectangleShape)
                    .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
            )

            Text(
                appInfo.name,
                style = AppTypography.headlineMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(24.dp)
            )

            TextSection(
                modifierRow,
                stringResource(R.string.version),
                appInfo.version
            )

            HorizontalDivider()

            TextSection(
                modifierRow,
                stringResource(R.string.package_app),
                appInfo.packageName
            )

            HorizontalDivider()

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                text = stringResource(R.string.check_sum),
                style = AppTypography.bodyLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp, horizontal = 32.dp),
                text = appInfo.checkSum,
                style = AppTypography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onButtonClick, modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                Text(stringResource(R.string.launch_app))
            }
        }
    }
}

@Composable
fun TextSection(modifierRow: Modifier, heading: String, description: String?) {
    Row(modifier = modifierRow) {
        Text(
            text = heading,
            style = AppTypography.bodyLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )

        Text(
            description ?: stringResource(R.string.unknown_data),
            style = AppTypography.bodyLarge,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}