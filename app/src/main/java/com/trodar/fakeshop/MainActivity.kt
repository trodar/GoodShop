package com.trodar.fakeshop

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.trodar.navigation.graph.RootNavigationGraph
import com.trodar.theme.themes.FakeShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private fun setupOnCreate() {
        window.setResizeSoftInputMode()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupOnCreate()
        enableEdgeToEdge()
        setContent {
            FakeShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    RootNavigationGraph(navController = navController)
                }
            }
        }
    }
}

fun Window.setResizeSoftInputMode() {
    @Suppress("DEPRECATION")
    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}
