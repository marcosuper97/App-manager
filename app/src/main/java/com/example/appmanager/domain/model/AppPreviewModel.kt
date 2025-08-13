package com.example.appmanager.domain.model

import android.graphics.drawable.Drawable

data class AppPreviewModel(
    val name: String,
    val version: String?,
    val icon: Drawable,
)