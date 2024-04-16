package com.trodar.utils.fetures.fragments

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigationIconContent: @Composable () -> Unit = { },
    actions: @Composable RowScope.() -> Unit = { },
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge
            )
        },
        colors = appBarColors(),
        navigationIcon = navigationIconContent,
        actions = actions,
        scrollBehavior = scrollBehavior,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeftAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigationIconContent: @Composable () -> Unit = { },
    actions: @Composable RowScope.() -> Unit = { },
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        colors = appBarColors(),
        navigationIcon = navigationIconContent,
        actions = actions,
        scrollBehavior = scrollBehavior,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun appBarColors()  = TopAppBarDefaults.centerAlignedTopAppBarColors(
    containerColor = MaterialTheme.colorScheme.background,
    titleContentColor = MaterialTheme.colorScheme.onBackground,
    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
    actionIconContentColor = MaterialTheme.colorScheme.onBackground,
)
