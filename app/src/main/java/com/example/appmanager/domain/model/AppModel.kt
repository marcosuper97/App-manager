package com.example.appmanager.domain.model

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toDrawable

data class AppModel(
    val name: String,
    val packageName: String,
    val version: String?,
    val icon: Drawable,
    val checkSum: String
) {
    companion object {
        // Создаем простой Drawable для тестов
        private val mockIcon = Color.BLUE.toDrawable()

        // Основной метод создания мока
        fun createTestModel(
            name: String = "Test App",
            packageName: String = "com.example.test",
            version: String = "1.0.0",
            icon: Drawable = mockIcon,
            checkSum: String = "sha256_test_hash"
        ) = AppModel(
            name = name,
            packageName = packageName,
            version = version,
            icon = icon,
            checkSum = checkSum
        )

        // Генератор списка тестовых моделей
        fun createTestList(count: Int = 3): List<AppModel> {
            return (1..count).map {
                createTestModel(
                    name = "App $it",
                    packageName = "com.test.app$it",
                    version = "1.0.$it"
                )
            }
        }
    }
}
