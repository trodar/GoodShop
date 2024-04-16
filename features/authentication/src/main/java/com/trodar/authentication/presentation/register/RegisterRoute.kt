package com.trodar.authentication.presentation.register

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.trodar.authentication.domain.AuthenticationUiState
import com.trodar.theme.R
import com.trodar.utils.Constants.PHONE_PREFIX
import com.trodar.utils.fetures.components.BackIconButton
import com.trodar.utils.fetures.fragments.CenterAppBar
import com.trodar.utils.fetures.showLongToast


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterRoute(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController,
    onLoginSuccess: () -> Unit,
) {

    val context = LocalContext.current

    val uiState by registerViewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.errorMessage.isNotEmpty()) {
        showLongToast(context, uiState.errorMessage)
        registerViewModel.errorMessageChange("")
    }


    Scaffold(
        topBar = {
            CenterAppBar(
                title = stringResource(id = R.string.register),
                navigationIconContent = {
                    BackIconButton {
                        registerViewModel.otpClear()
                        navController.popBackStack()
                    }
                }
            )
        }

    ) { padding ->
        when (uiState) {
            is AuthenticationUiState.Registration ->
                RegisterScreen(
                    uiState = uiState,
                    paddingValues = padding,
                    phonePrefix = PHONE_PREFIX,
                    onAdvtChange = { registerViewModel.profileChange(uiState.profile.copy(advt = it)) },
                    onNameChange = { registerViewModel.profileChange(uiState.profile.copy(user = it)) },
                    onEmailChange = { registerViewModel.profileChange(uiState.profile.copy(email = it)) },
                    onPhoneChange = { registerViewModel.profileChange(uiState.profile.copy(phone = it)) },
                    onImageChange = {

                        registerViewModel.profileChange(uiState.profile.copy(photoUrl = it?.toString()))
                    },
                    onRegisterClick = {

                        if (uiState.profile.isRegisterValid) {
                            registerViewModel.signUp(context)
                        } else {
                            showLongToast(context, context.getString(R.string.valid_error))
                        }
                    },
                )

            is AuthenticationUiState.OtpSent -> {
                val keyboardController = LocalSoftwareKeyboardController.current
                OtpSentScreen(
                    uiState = uiState,
                    paddingValues = padding,
                    onVerificationChange = { registerViewModel.verificationChange(it) }
                ) {
                    registerViewModel.signIn(
                        context
                    ) {
                        keyboardController?.hide()
                        onLoginSuccess()
                    }
                }
            }
        }
    }
}