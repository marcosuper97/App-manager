package com.example.appmanager.ui.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmanager.domain.model.AppModel
import com.example.appmanager.ui.theme.AppManagerTheme
import com.example.appmanager.util.toBitmap

@Composable
fun MainScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun AppCard(modifier: Modifier = Modifier) {
    Card(modifier
        .fillMaxWidth()
        .height(60.dp),
        ) {
        Row {
        }
    }
}

@Composable
fun AppIcon(modifier: Modifier, app: AppModel){
    Image(
        painter = BitmapPainter(app.icon.toBitmap().asImageBitmap()),
        contentDescription = "Иконка приложения ${app.name}",
        modifier = Modifier.size(48.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppManagerTheme {
        MainScreen("Android")
    }
}