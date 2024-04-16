package com.trodar.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.trodar.catalog.catalog_list.CatalogRoute
import com.trodar.catalog.categories_brands.CategoriesBrandsRoute
import com.trodar.catalog.product_card.CardListRoute
import com.trodar.home.HomeDetailRoute
import com.trodar.home.HomeRoute
import com.trodar.navigation.FakeScreen
import com.trodar.navigation.route.BottomRoute
import com.trodar.order.OrderDetailRoute
import com.trodar.order.OrderRoute
import com.trodar.settings.presentation.contact_us.ContactUsRoute
import com.trodar.settings.presentation.profile.ProfileRoute
import com.trodar.settings.presentation.setting.SettingsRoute
import com.trodar.settings.presentation.term_privacy.TermRoute

@Composable
fun MainNavGraph(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        route = RootGraph.MAIN,
        startDestination = BottomRoute.Home.route
    ) {

        addHomeRoute(navHostController = navHostController)

        addCatalogRoute(navHostController = navHostController)

        addOrderRoute(navHostController = navHostController)

        addSettingsRoute(navHostController = navHostController)
    }
}

fun NavGraphBuilder.addHomeRoute(
    navHostController: NavHostController,
) {
    navigation(
        route = BottomRoute.Home.route,
        startDestination = HomeScreen.General.route
    ) {

        composable(route = HomeScreen.General.route) {
            HomeRoute {id ->
                navHostController.navigate(HomeScreen.ProductDetail.passId(id))
            }
        }
        composable(route = HomeScreen.ProductDetail.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val id = it.arguments?.getInt("id") ?: 0

            HomeDetailRoute(id = id, navController = navHostController)
        }
    }
}

fun NavGraphBuilder.addCatalogRoute(
    navHostController: NavHostController
) {
    navigation(
        route = BottomRoute.Catalog.route,
        startDestination = CatalogScreen.CatalogList.route
    ) {
        composable(route = CatalogScreen.CatalogList.route) {
            CatalogRoute(
                onCategoriesClick = { navHostController.navigate(CatalogScreen.CategoriesBrandsList.route) },
                onProductClick = { navHostController.navigate(CatalogScreen.CardList.route) },
            )
        }
        composable(route = CatalogScreen.CategoriesBrandsList.route) {
            CategoriesBrandsRoute(navController = navHostController)
        }
        composable(route = CatalogScreen.CardList.route) {
            CardListRoute(navController = navHostController)
        }
    }
}

fun NavGraphBuilder.addOrderRoute(
    navHostController: NavHostController
) {
    navigation(
        route = BottomRoute.Order.route,
        startDestination = OrderScreen.OrderList.route
    ) {
        composable(route = OrderScreen.OrderList.route) {
            OrderRoute { id ->
                navHostController.navigate(OrderScreen.Order.passId(id))
            }
        }
        composable(
            route = OrderScreen.Order.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            OrderDetailRoute(id = id, navController = navHostController) {
                navHostController.navigate(CatalogScreen.CatalogList.route)
            }
        }
    }
}

fun NavGraphBuilder.addSettingsRoute(
    navHostController: NavHostController,
) {
    navigation(
        route = BottomRoute.Settings.route,
        startDestination = SettingsScreen.Settings.route
    ) {
        composable(route = SettingsScreen.Settings.route) {
            SettingsRoute(
                onContactUsClick = { navHostController.navigate(SettingsScreen.ContactUs.route) },
                onTermClick = { navHostController.navigate(SettingsScreen.Terms.route) },
                onNotificationClick = { navHostController.navigate(SettingsScreen.Notification.route) },
                onProfileClick = { navHostController.navigate(SettingsScreen.EditProfile.route) },
            )
        }
        composable(route = SettingsScreen.EditProfile.route) {
            ProfileRoute(navController = navHostController)
        }
        composable(route = SettingsScreen.Notification.route) {
            FakeScreen(value = SettingsScreen.Notification.route) {}
        }
        composable(route = SettingsScreen.Terms.route) {
            TermRoute(navController = navHostController)
        }
        composable(route = SettingsScreen.ContactUs.route) {
            ContactUsRoute(navController = navHostController)
        }
    }
}

sealed class HomeScreen(val route: String) {
    data object General: HomeScreen(route = "HOME_GENERAL")
    data object ProductDetail: HomeScreen(route = "HOME_PRODUCT_DETAIL/{id}") {
        fun passId(id: Int): String {
            return "HOME_PRODUCT_DETAIL/$id"
        }

    }
}

sealed class SettingsScreen(val route: String) {
    data object Settings : SettingsScreen(route = "SETTINGS_SCREEN")
    data object EditProfile : SettingsScreen(route = "EDIT_PROFILE")
    data object Terms : SettingsScreen(route = "TERMS")
    data object ContactUs : SettingsScreen(route = "CONTACT_US")
    data object Notification : SettingsScreen(route = "NOTIFICATION")
}

sealed class OrderScreen(val route: String) {
    data object OrderList : OrderScreen(route = "ORDER_LIST")
    data object Order : OrderScreen(route = "ORDER_ITEM/{id}") {
        fun passId(id: Int): String {
            return "ORDER_ITEM/$id"
        }
    }
}

sealed class CatalogScreen(val route: String) {
    data object CatalogList : CatalogScreen(route = "CATALOG_LIST")
    data object CategoriesBrandsList : CatalogScreen(route = "CATALOG_CATEGORIES_BRANDS")
    data object CardList : CatalogScreen(route = "CATALOG_CARD_LIST")
}



