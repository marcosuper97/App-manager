package com.example.appmanager.data.impl

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.example.appmanager.domain.api.AppsRepository
import com.example.appmanager.domain.model.AppModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AppsRepositoryImpl(
    private val packageManager: PackageManager,
    private val context: Context
) : AppsRepository {

    private fun fetchApplicationInfo(): List<ApplicationInfo> {
        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    }

    private fun ApplicationInfo.mapToModel(): AppModel {
        return AppModel(
            name = packageManager
                .getApplicationLabel(this)
                .toString(),
            packageName = packageName,
            version = getPackageVersion(),
            icon = loadIcon(packageManager),
            sourceDir = sourceDir
        )
    }

    private fun ApplicationInfo.getPackageVersion(): String? {
        return try {
            packageManager.getPackageInfo(packageName, 0).versionName
        } catch (e: Exception) {
            null
        }
    }

    override fun getInstalledApplications():
            Flow<List<AppModel>> = callbackFlow {
        trySend(fetchApplicationInfo().map { it.mapToModel() })

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                trySend(fetchApplicationInfo().map { it.mapToModel() })
            }
        }

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_PACKAGE_ADDED)
            addAction(Intent.ACTION_PACKAGE_REMOVED)
            addDataScheme("package")
        }

        context.registerReceiver(receiver, filter)

        awaitClose {
            context.unregisterReceiver(receiver)
        }
    }
}