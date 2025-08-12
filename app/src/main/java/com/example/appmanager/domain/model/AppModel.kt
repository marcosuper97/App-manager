package com.example.appmanager.domain.model

import android.graphics.drawable.Drawable

data class AppModel(
    val name: String,
    val packageName: String,
    val version: String?,
    val icon: Drawable,
    val checkSum: String
)