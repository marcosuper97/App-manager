package com.example.appmanager.data.impl

import android.content.Context
import android.content.pm.PackageManager
import com.example.appmanager.domain.api.AppLauncherRepository

class AppLauncherRepositoryImpl(
    private val packageManager: PackageManager,
    private val context: Context
) :
    AppLauncherRepository {
    override fun launchApp(packageName: String) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            context.startActivity(intent)
        } else {
            // Обработка, если приложение не установлено
        }
    }
}