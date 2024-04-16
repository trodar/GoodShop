package com.trodar.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.trodar.navigation.FakeScreen
import com.trodar.navigation.route.MainRoute


@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = RootGraph.ROOT,
        startDestination = RootGraph.AUTHENTICATION) {

        authNavGraph(navController)
        composable(route = RootGraph.MAIN) {
            MainRoute()
        }
    }
}

object RootGraph{
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "main_graph"
}