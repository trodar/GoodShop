package com.trodar.settings.presentation.setting

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trodar.model.Profile
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants.MODIFIER
import com.trodar.utils.Constants.SPACER16


@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    profile: Profile,
    uri: Uri? = null,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onContactUsClick: () -> Unit,
    onTermClick: () -> Unit,
) {

    val scrollState = rememberScrollState()
    Column(
        modifier = MODIFIER
            .padding(top = paddingValues.calculateTopPadding())
            .padding(top = SPACER16)
            .padding(bottom = paddingValues.calculateBottomPadding())
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background),
    )
    {
        ProfileCard(
            profile = profile,
            uri = uri,
            onProfileClick = onProfileClick
        )

        Spacer(modifier = Modifier.height(SPACER16))
        Spacer(modifier = Modifier.height(SPACER16))
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        SettingItem(title = "Change language", "ENG", onClick = {})
        SettingItem(title = "Notification", onClick = onNotificationClick)
        SettingItem(title = "Contact Us", onClick = onContactUsClick)
        SettingItem(title = "Term & privacy", onClick = onTermClick)
    }
}

@Composable
fun SettingItem(
    title: String,
    optText: String = "",
    onClick: () -> Unit
){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.weight(1f))
            if (optText.isNotEmpty())
                Text(text = optText, color = MaterialTheme.colorScheme.onBackground)

            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = "profile",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
    }
}

@Preview(
    "Settings screen",
    showBackground = true,
    device = Devices.NEXUS_5
)
@Preview(
    "Settings screen (dark)",
    showBackground = true,
    device = Devices.NEXUS_5,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun RegisterScreenPreview() {
    FakeShopTheme {
        SettingsScreen(
            paddingValues = PaddingValues(0.dp),
            profile = Profile(
                user = "Eugene M.",
                phone = "+38(068)777-66-44",
                email = "mack@gmail.com"
            ),
            uri = Uri.parse("https://i.imgur.com/yhW6Yw1.jpeg"),
            onProfileClick = {},
            onContactUsClick = {},
            onNotificationClick = {},
            onTermClick = {},
        )
    }
}