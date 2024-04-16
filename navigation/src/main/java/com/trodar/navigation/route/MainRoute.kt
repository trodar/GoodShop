package com.trodar.navigation.route

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.trodar.navigation.graph.CatalogScreen
import com.trodar.navigation.graph.HomeScreen
import com.trodar.navigation.graph.MainNavGraph
import com.trodar.navigation.graph.OrderScreen
import com.trodar.navigation.graph.SettingsScreen

@Composable
fun MainRoute(navHostController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navHostController) }

    ) { paddingValues ->
        MainNavGraph(
            navHostController = navHostController,
            paddingValues = paddingValues
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val bottomList = getBottomItems()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = bottomList.any { HomeScreen.General.route == currentDestination?.route } ||
            SettingsScreen.Settings.route == currentDestination?.route ||
            OrderScreen.OrderList.route == currentDestination?.route ||
            CatalogScreen.CatalogList.route == currentDestination?.route
    if (bottomBarDestination) {
        NavigationBar {
            bottomList.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomRoute,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = stringResource(screen.title))
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(screen.icon),
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,

        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}