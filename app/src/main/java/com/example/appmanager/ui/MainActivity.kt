package com.example.appmanager.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appmanager.ui.app_details.AppDetailsScreen
import com.example.appmanager.ui.main_screen.MainScreen
import com.example.appmanager.ui.theme.AppManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppManagerTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(navController)
        }

        composable("detailsScreen/{packageName}") { backStackEntry ->
            val packageName = backStackEntry.arguments?.getString("packageName")
            AppDetailsScreen(packageName, navController)
        }

    }
}