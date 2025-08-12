package com.example.appmanager.ui.main_screen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmanager.domain.model.AppModel
import com.example.appmanager.ui.theme.AppManagerTheme
import com.example.appmanager.ui.theme.AppTypography
import com.example.appmanager.util.toBitmap

@Composable
fun AppCard(
    modifier: Modifier = Modifier.padding(
        horizontal = 8.dp,
        vertical = 4.dp
    ),
    appInfo: AppModel,
//    onCardClick: () -> Unit
) {
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .height(82.dp)
//            .clickable(
//                true,
//                onClick = onCardClick
//            )
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
                Text(appInfo.name,
                    style = AppTypography.titleMedium
                )
                Text(appInfo.version.toString(),
                    style = AppTypography.labelMedium)
            }
        }
    }
}

@Preview(showBackground = false, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CardPreviewNight() {
    AppManagerTheme {
        AppCard(appInfo = AppModel.createTestModel())
    }
}

@Preview(showBackground = false, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun CardPreviewDay() {
    AppManagerTheme {
        AppCard(appInfo = AppModel.createTestModel())
    }
}