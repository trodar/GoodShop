package com.trodar.authentication.presentation.register

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trodar.authentication.domain.AuthenticationUiState
import com.trodar.model.Profile
import com.trodar.theme.R
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants.PHONE_MASK
import com.trodar.utils.Constants.SPACER16
import com.trodar.utils.fetures.components.CornerShapeButton
import com.trodar.utils.fetures.components.CustomCircularProgressBar
import com.trodar.utils.fetures.fragments.ProfileItem
import com.trodar.utils.Constants.MODIFIER

@Composable
fun RegisterScreen(
    uiState: AuthenticationUiState,
    paddingValues: PaddingValues,
    phonePrefix: String,
    onAdvtChange: (Boolean) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onImageChange: (Uri?) -> Unit,
    onRegisterClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val activateError = remember { mutableStateOf(false) }

    val uri = if (uiState.profile.photoUrl.isNullOrBlank()) null else Uri.parse(uiState.profile.photoUrl)

    if (uiState.isLoading) {
        CustomCircularProgressBar()
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .padding(bottom = paddingValues.calculateBottomPadding())
            .systemBarsPadding()
            .imePadding()
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(SPACER16))

        ProfileItem(
            profile = uiState.profile,
            uri = uri,
            modifier = MODIFIER,
            spacerHeight = SPACER16,
            phoneMask = PHONE_MASK,
            phonePrefix = phonePrefix,
            activateError = activateError.value,
            onNameChange = onNameChange,
            onEmailChange = onEmailChange,
            onPhoneChange = onPhoneChange,
            onImageChange = onImageChange,
            onAdvtChange = onAdvtChange,
        )

        Spacer(modifier = Modifier.weight(1f))

        CornerShapeButton(
            text = stringResource(R.string.register),
            modifier = MODIFIER.padding(bottom = SPACER16),
            onClick = {
                activateError.value = true
                focusManager.clearFocus()
                onRegisterClick()
            }
        )
    }
}

@Preview(
    "Register screen",
    showBackground = true,
    device = Devices.NEXUS_5
)
@Preview(
    "Register screen (dark)",
    showBackground = true,
    device = Devices.NEXUS_5,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun RegisterScreenPreview() {
    FakeShopTheme {
        val profile = AuthenticationUiState.Registration(
            isLoading = false,
            errorMessage = "",
            profile = Profile(),
            )
        RegisterScreen(
            profile,
            PaddingValues(0.dp),
            "+38",
            { _ -> },
            { _ -> },
            { _ -> },
            { _ -> },
            { _ -> },
            { },
        )
    }
}