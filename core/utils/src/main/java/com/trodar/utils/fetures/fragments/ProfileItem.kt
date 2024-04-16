package com.trodar.utils.fetures.fragments

import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.trodar.model.Profile
import com.trodar.model.validation.validEmail
import com.trodar.model.validation.validName
import com.trodar.model.validation.validPhone
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.R
import com.trodar.utils.Constants.SPACER12
import com.trodar.utils.Constants.SPACER16
import com.trodar.utils.fetures.components.CustomTextField
import com.trodar.utils.fetures.components.PhoneField
import com.trodar.utils.Constants.MODIFIER

@Composable
fun ProfileItem(
    profile: Profile,
    modifier: Modifier = Modifier,
    uri: Uri? = null,
    spacerHeight: Dp = SPACER16,
    phoneMask: String = "000 000 00 00",
    phonePrefix: String = "+7",
    activateError: Boolean,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onImageChange: (Uri?) -> Unit,
    onAdvtChange: (Boolean) -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uriEvent: Uri? ->
        onImageChange(uriEvent)
    }
    val phone = if (profile.phone.indexOf(phonePrefix) < 0) profile.phone
    else profile.phone.substring(phonePrefix.length)

    Spacer(modifier = Modifier.height(spacerHeight))
    ProfileImage(
        uri = uri,
        onClick = { launcher.launch("image/*") },
    )

    Spacer(modifier = Modifier.height(spacerHeight))
    Text(
        text = stringResource(R.string.your_name),
        modifier = modifier,
        color = MaterialTheme.colorScheme.onBackground
    )
    CustomTextField(modifier = modifier,
        value = profile.user,
        keyboardType = KeyboardType.Text,
        activateError = activateError,
        onValueChange = onNameChange,
        onValidation = { validName(it) })

    Spacer(modifier = Modifier.height(spacerHeight))
    Text(
        text = stringResource(R.string.email),
        modifier = modifier,
        color = MaterialTheme.colorScheme.onBackground
    )
    CustomTextField(modifier = modifier,
        value = profile.email,
        keyboardType = KeyboardType.Email,
        activateError = activateError,
        onValueChange = onEmailChange,
        onValidation = { validEmail(it) })

    Spacer(modifier = Modifier.height(spacerHeight))
    Text(
        text = stringResource(R.string.phone_number),
        modifier = modifier,
        color = MaterialTheme.colorScheme.onBackground
    )
    PhoneField(modifier = modifier.background(
        MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(SPACER12)
    ),
        prefix = phonePrefix,
        phone = phone,
        mask = phoneMask,
        maskNumber = '0',
        activateError = activateError,
        onPhoneChanged = onPhoneChange,
        onValidation = { validPhone(it) })
    Spacer(modifier = Modifier.height(spacerHeight))
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = profile.advt, onCheckedChange = { onAdvtChange(it) })
        Text(
            text = stringResource(com.trodar.theme.R.string.adt),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ProfileImage(
    uri: Uri? = null,
    onClick: () -> Unit,
) {
    val imageSize = 128.dp
    val modifier = Modifier
        .size(imageSize)
        .clip(CircleShape)

        .clickable { onClick() }

    if (uri == null) {
        Image(
            painter = painterResource(R.drawable.user_add),
            contentDescription = "add contact",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = modifier
        )
    } else {
        val imagePainter = rememberAsyncImagePainter(model = uri)
        Image(
            imagePainter,
            contentDescription = "ss",
            contentScale = ContentScale.Crop,
            modifier = modifier.border(2.dp, Color.LightGray, CircleShape)

        )

    }
}

fun testProfile(): Profile {
    return Profile(
        user = "mack",
        phone = "068 777 66 44",
        email = "mack@gmail.com",
    )
}

@Preview(
    "Profile item", showBackground = true, device = Devices.NEXUS_5
)
@Preview(
    "Profile item (dark)",
    showBackground = true,
    device = Devices.NEXUS_5,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun RegisterScreenPreview() {
    FakeShopTheme {
        Column(
            modifier = MODIFIER
                .systemBarsPadding()
                .imePadding()
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProfileItem(
                profile = testProfile(),
                modifier = Modifier.fillMaxWidth(),
                spacerHeight = SPACER16,
                phoneMask = "000 000 00 00",
                phonePrefix = "+38",
                activateError = false,
                onNameChange = {},
                onEmailChange = {},
                onPhoneChange = {},
                onImageChange = {},
                onAdvtChange = {},
            )
        }
    }
}




























