package com.example.appmanager.ui.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import com.example.appmanager.domain.model.AppPreviewModel
import com.example.appmanager.ui.theme.AppTypography
import com.example.appmanager.util.toBitmap

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    appInfo: AppPreviewModel,
) {
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .height(82.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Image(
                painter = BitmapPainter(appInfo.icon.toBitmap().asImageBitmap()),
                contentDescription = "Иконка приложения ${appInfo.name}",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
            )

            Column(modifier.padding(horizontal = 8.dp)) {
                Text(
                    appInfo.name,
                    style = AppTypography.titleMedium
                )
                Text(
                    appInfo.version.toString(),
                    style = AppTypography.labelMedium
                )
            }
        }
    }
}