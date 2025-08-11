package com.example.appmanager.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter

fun Drawable.toBitmap(): Bitmap {
    if (this is BitmapDrawable && bitmap != null) {
        return bitmap
    }
    val bitmap = Bitmap.createBitmap(
        intrinsicWidth.coerceAtLeast(1),
        intrinsicHeight.coerceAtLeast(1),
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)
    return bitmap
}