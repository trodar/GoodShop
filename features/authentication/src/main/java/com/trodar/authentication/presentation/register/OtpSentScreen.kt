package com.trodar.authentication.presentation.register

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trodar.authentication.domain.AuthenticationUiState
import com.trodar.model.Verification
import com.trodar.model.Profile
import com.trodar.theme.R
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.fetures.components.CustomCircularProgressBar
import com.trodar.utils.fetures.components.CornerShapeButton
import com.trodar.utils.fetures.components.CustomTextField
import com.trodar.utils.Constants.SPACER16


@Composable
fun OtpSentScreen(
    uiState: AuthenticationUiState,
    paddingValues: PaddingValues,
    onVerificationChange: (Verification) -> Unit,
    onNextClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val verification = (uiState as? AuthenticationUiState.OtpSent)?.verification ?: Verification()

    val modifier = Modifier
        .padding(horizontal = SPACER16)
        .fillMaxWidth()

    if (uiState.isLoading) {
        CustomCircularProgressBar()
    }
    Column(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .systemBarsPadding()
            .imePadding()
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(SPACER16))

        Text(text = stringResource(R.string.otp_send_number_text, uiState.profile.phone))

        Spacer(modifier = Modifier.height(SPACER16))

        Text(
            text = stringResource(R.string.enter_code),
            fontSize = 12.sp,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(4.dp))
        CustomTextField(
            value = verification.otpNumber,
            keyboardType = KeyboardType.Number,
            activateError = false,
            onValueChange = { onVerificationChange(verification.copy(otpNumber = it)) },
            onValidation = { true }
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(R.string.get_a_new_code_in, "0:28"),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(SPACER16))
        CornerShapeButton(
            text = stringResource(R.string.next),
            modifier = modifier.padding(bottom = SPACER16),
            onClick = {
                focusManager.clearFocus()
                onNextClick()
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
fun OtpSentScreenPreview() {
    FakeShopTheme {
        val profile = AuthenticationUiState.Registration(
            isLoading = false,
            errorMessage = "",
            profile = Profile(),
        )
        OtpSentScreen(
            profile,
            PaddingValues(0.dp),
            { _ -> },
            { },
        )
    }
}