package com.example.appmanager.data.impl

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.example.appmanager.domain.api.AppsRepository
import com.example.appmanager.domain.model.AppModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn
import java.io.File
import java.security.MessageDigest

class AppsRepositoryImpl(
    private val packageManager: PackageManager,
    private val context: Context
) : AppsRepository {

    private val appsFlow = callbackFlow {
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
    }.shareIn(
        scope = CoroutineScope(SupervisorJob()),
        started = SharingStarted.WhileSubscribed(5000),
        replay = 1
    )

    private fun fetchApplicationInfo(): List<ApplicationInfo> {
        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { appInfo ->
                (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0
            }
    }

    private fun ApplicationInfo.mapToModel(): AppModel {
        return AppModel(
            name = packageManager
                .getApplicationLabel(this)
                .toString(),
            packageName = packageName,
            version = getPackageVersion(),
            icon = loadIcon(packageManager),
            checkSum = calcChecksum(sourceDir, SHA_256)
        )
    }

    private fun ApplicationInfo.getPackageVersion(): String? {
        return try {
            packageManager.getPackageInfo(packageName, 0).versionName
        } catch (e: Exception) {
            null
        }
    }

    private fun calcChecksum(filePath: String, algorithm: String): String {
        val digest = MessageDigest.getInstance(algorithm)
        File(filePath).inputStream().use { input ->
            val buffer = ByteArray(BUFFER_SIZE)
            var bytesRead: Int
            while (input.read(buffer).also { bytesRead = it } != -1) {
                digest.update(buffer, 0, bytesRead)
            }
        }
        return digest.digest().joinToString("") { "%02x".format(it) }
    }

    override fun getInstalledApplications(): Flow<List<AppModel>> = appsFlow

    companion object {
        private const val BUFFER_SIZE = 8192
        private const val SHA_256 = "SHA-256"
    }
}