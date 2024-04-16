package com.trodar.settings.presentation.setting

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.currentStateAsState
import com.trodar.theme.R
import com.trodar.utils.fetures.fragments.CenterAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsRoute(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onContactUsClick: () -> Unit,
    onTermClick: () -> Unit,
) {

    val uiState by settingsViewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateAsState()

    Scaffold(
        topBar = {
            CenterAppBar(
                title = stringResource(id = R.string.settings),
            )
        }
    ) { paddingValues ->
        SettingsScreen(
            paddingValues = paddingValues,
            profile = uiState.profile,
            uiState.profileImageUri,
            onProfileClick = {
                onProfileClick()
                settingsViewModel.needUpdateChange(true)
            },
            onNotificationClick = onNotificationClick,
            onTermClick = onTermClick,
            onContactUsClick = onContactUsClick,
        )
    }

    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.RESUMED -> {
                settingsViewModel.checkForUpdateProfile()
            }
            else -> {}
        }
    }
}