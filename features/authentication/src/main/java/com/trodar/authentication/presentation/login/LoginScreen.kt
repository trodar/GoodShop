package com.trodar.authentication.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trodar.authentication.R
import com.trodar.authentication.domain.AuthenticationUiState
import com.trodar.model.Profile
import com.trodar.model.Verification
import com.trodar.model.validation.validPhone
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants.MODIFIER
import com.trodar.utils.Constants.PHONE_MASK
import com.trodar.utils.Constants.PHONE_PREFIX
import com.trodar.utils.Constants.SPACER12
import com.trodar.utils.Constants.SPACER16
import com.trodar.utils.fetures.components.CornerShapeButton
import com.trodar.utils.fetures.components.CustomCircularProgressBar
import com.trodar.utils.fetures.components.CustomTextField
import com.trodar.utils.fetures.components.PhoneField

@Composable
fun LoginScreen(
    uiState: AuthenticationUiState,
    paddingValues: PaddingValues,
    onPhoneChange: (String) -> Unit,
    onVerificationChange: (Verification) -> Unit,
    onSignIn: () -> Unit,
    onSignUpClick: () -> Unit,
    onSendPhoneNumberClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    var activateError by remember { mutableStateOf(false) }
    val verification = (uiState as? AuthenticationUiState.OtpSent)?.verification ?: Verification()

    if (uiState.isLoading) {
        CustomCircularProgressBar()
    }

    Column(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .padding(bottom = paddingValues.calculateBottomPadding())
            .systemBarsPadding()
            .imePadding()
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)

    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()

        ) {
            Image(
                painter = painterResource(id = R.drawable.loging_logo),
                contentDescription = "logo",
                modifier = Modifier.size(128.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(com.trodar.utils.R.string.phone_number),
            modifier = MODIFIER
        )

        PhoneField(
            modifier = MODIFIER.background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(SPACER12)
            ),
            prefix = PHONE_PREFIX,
            phone = uiState.profile.phone,
            mask = PHONE_MASK,
            activateError = activateError,
            onPhoneChanged = onPhoneChange,
            onValidation = { validPhone(it) },
            trailingIcon = {
                IconButton(onClick = onSendPhoneNumberClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.Send,
                        contentDescription = "send",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(SPACER16))

        if (uiState is AuthenticationUiState.OtpSent) {
            Text(
                text = stringResource(
                    com.trodar.theme.R.string.otp_send_number_text,
                    uiState.profile.phone
                ),
                modifier = MODIFIER
            )
            CustomTextField(
                modifier = MODIFIER,
                value = verification.otpNumber,
                keyboardType = KeyboardType.Number,
                activateError = false,
                onValueChange = { onVerificationChange(verification.copy(otpNumber = it)) },
                onValidation = { true }
            )
        }

        Spacer(modifier = Modifier.height(SPACER16))

        CornerShapeButton(
            text = stringResource(com.trodar.theme.R.string.login),
            enabled = uiState is AuthenticationUiState.OtpSent,
            modifier = MODIFIER.padding(bottom = SPACER16),
            onClick = {
                activateError = true
                focusManager.clearFocus()
                onSignIn()
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(com.trodar.theme.R.string.not_have_account))
            TextButton(onClick = onSignUpClick) {
                Text(
                    text = stringResource(com.trodar.theme.R.string.sign_up),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(SPACER16))
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
        val profile = AuthenticationUiState.OtpSent(
            isLoading = false,
            errorMessage = "",
            verification = Verification(),
            profile = Profile(),

            )
        LoginScreen(
            profile,
            PaddingValues(0.dp),
            { _ -> },
            { _ -> },
            { },
            { },
            { },
        )
    }
}