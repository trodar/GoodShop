package com.trodar.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.trodar.authentication.presentation.login.LoginRoute
import com.trodar.authentication.presentation.register.RegisterRoute
import com.trodar.navigation.FakeScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = RootGraph.AUTHENTICATION,
        startDestination = AuthScreen.SignIn.route
    ) {
        composable(route = AuthScreen.SignIn.route) {
            LoginRoute(
                onSignUpClick = { navController.navigate(AuthScreen.SignUp.route) }
            ) { navController.navigate(RootGraph.MAIN) }
        }

        composable(route = AuthScreen.Forgot.route) {
            FakeScreen(value = AuthScreen.Forgot.route) {}

        }

        composable(route = AuthScreen.SignUp.route) {
            RegisterRoute(navController = navController) {
                navController.navigate(RootGraph.MAIN)
            }
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object SignIn : AuthScreen(route = "LOGIN")
    data object SignUp : AuthScreen(route = "SIGN_UP")
    data object Forgot : AuthScreen(route = "FORGOT")
}