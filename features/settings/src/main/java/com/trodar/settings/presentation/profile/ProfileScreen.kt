package com.trodar.settings.presentation.profile

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
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
import com.trodar.model.Profile
import com.trodar.theme.R
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants.MODIFIER
import com.trodar.utils.Constants.SPACER16
import com.trodar.utils.fetures.components.CornerShapeButton
import com.trodar.utils.fetures.fragments.ProfileItem
import com.trodar.utils.fetures.fragments.testProfile

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues,
    profile: Profile,
    uri: Uri? = null,
    onLogOutClick: () -> Unit,
    onAdvtChange: (Boolean) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onImageChange: (Uri?) -> Unit,
    onSaveClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val activateError = remember { mutableStateOf(false) }
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
        ProfileItem(
            profile = profile,
            uri = uri,
            modifier = MODIFIER,
            phonePrefix = "+38",
            activateError = activateError.value,
            onNameChange = onNameChange,
            onEmailChange = onEmailChange,
            onPhoneChange = onPhoneChange,
            onImageChange = onImageChange,
            onAdvtChange = onAdvtChange
        )
        Row {
            CornerShapeButton(
                text = stringResource(R.string.save),
                modifier = Modifier.weight(1f).padding(start = SPACER16),
                onClick = onSaveClick
            )
            Spacer(modifier = Modifier.width(4.dp))
            CornerShapeButton(
                text = stringResource(R.string.log_out),
                modifier = Modifier.weight(1f).padding(end = SPACER16),
                onClick = {
                    focusManager.clearFocus()
                    onLogOutClick()
                }
            )
        }
    }
}

@Preview(
    "Profile screen",
    showBackground = true,
    device = Devices.NEXUS_5
)
@Preview(
    "Profile screen (dark)",
    showBackground = true,
    device = Devices.NEXUS_5,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun RegisterScreenPreview() {
    FakeShopTheme {
        ProfileScreen(
            profile = testProfile(),
            paddingValues = PaddingValues(0.dp),
            onLogOutClick = {},
            onNameChange = { _ -> },
            onEmailChange = { _ -> },
            onPhoneChange = { _ -> },
            onAdvtChange = { _ -> },
            onImageChange = { _ -> },
            onSaveClick = {}
        )
    }
}