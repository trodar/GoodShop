package com.trodar.authentication.presentation.login

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.trodar.theme.R
import com.trodar.utils.fetures.fragments.CenterAppBar
import com.trodar.utils.fetures.showLongToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onSignUpClick: () -> Unit,
    onSuccess: () -> Unit,
) {

    val context = LocalContext.current
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    if (uiState.errorMessage.isNotEmpty()) {
        showLongToast(context, uiState.errorMessage)
        loginViewModel.errorMessageChange("")
    }

    Scaffold(
        topBar = {
            CenterAppBar(
                title = stringResource(id = R.string.login),
            )
        }

    ) { paddingValues ->
        LoginScreen(
            uiState = uiState,
            paddingValues = paddingValues,
            onPhoneChange = { loginViewModel.profileChange(uiState.profile.copy(phone = it)) },
            onVerificationChange = { loginViewModel.verificationChange(it) },
            onSignIn = {
                loginViewModel.signIn(
                    context
                ) {
                    keyboardController?.hide()
                    onSuccess()
                }
            },
            onSignUpClick = onSignUpClick,
            onSendPhoneNumberClick = {
                if (uiState.profile.isLoginValid) {
                    loginViewModel.signUp(context)
                } else {
                    showLongToast(context, context.getString(R.string.valid_error))
                }
            },
        )
    }
}
