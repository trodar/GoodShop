package com.trodar.navigation.route

import androidx.annotation.StringRes
import com.trodar.navigation.R

fun getBottomItems() = listOf(
    BottomRoute.Home,
    BottomRoute.Catalog,
    BottomRoute.Order,
    BottomRoute.Settings,
)
sealed class BottomRoute(
    val route: String,
    @StringRes val title: Int,
    val icon: Int,
) {
    data object Home: BottomRoute(
        route = "HOME",
        title = com.trodar.theme.R.string.bottom_home,
        icon = R.drawable.bottom_home_24
    )

    data object Catalog: BottomRoute(
        route = "CATALOG",
        title = com.trodar.theme.R.string.bottom_catalog,
        icon = R.drawable.bottom_catalog_24
    )

    data object Order: BottomRoute(
        route = "ORDER",
        title = com.trodar.theme.R.string.bottom_order,
        icon = R.drawable.bottom_order_24
    )

    data object Settings: BottomRoute(
        route = "SETTINGS",
        title = com.trodar.theme.R.string.bottom_setting,
        icon = R.drawable.bottom_settings_24
    )
}