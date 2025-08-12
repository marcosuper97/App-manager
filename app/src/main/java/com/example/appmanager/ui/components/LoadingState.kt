package com.example.appmanager.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.appmanager.R
import com.example.appmanager.ui.theme.AppTypography
import com.example.appmanager.ui.theme.displayFontFamily
import kotlinx.coroutines.delay

@Composable
fun LoadingState() {
    val frazes = listOf(
        stringResource(R.string.fraze_first),
        stringResource(R.string.fraze_second),
        stringResource(R.string.fraze_third)
    )
    var currentPhraseIndex by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(700L) // пауза 2 секунды
            currentPhraseIndex = (currentPhraseIndex + 1) % frazes.size
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                stringResource(R.string.loading_data),
                style = AppTypography.headlineSmall,
                fontFamily = displayFontFamily
            )
            Text(
                text = frazes[currentPhraseIndex],
                style = AppTypography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}