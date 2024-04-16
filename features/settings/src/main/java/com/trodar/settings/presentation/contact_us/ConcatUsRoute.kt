package com.trodar.settings.presentation.contact_us

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.trodar.theme.R
import com.trodar.theme.colors.textFieldColors
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants.SPACER12
import com.trodar.utils.Constants.SPACER16
import com.trodar.utils.extensions.canGoBack
import com.trodar.utils.fetures.components.BackIconButton
import com.trodar.utils.fetures.components.CornerShapeButton
import com.trodar.utils.fetures.fragments.CenterAppBar
import com.trodar.utils.fetures.showShortToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsRoute(
    concatUsViewModel: ConcatUsViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val uiState by concatUsViewModel.uiState.collectAsState()
    val context = LocalContext.current
    Scaffold(

        topBar = {
            CenterAppBar(
                title = stringResource(id = R.string.contact_us),
                navigationIconContent = {
                    BackIconButton {
                        if (navController.canGoBack)
                            navController.popBackStack()
                    }
                }
            )
        }
    ) { paddingValues ->
        ContactUsScreen(
            paddingValues = paddingValues,
            title = uiState.title,
            message = uiState.message,
            onTitleChange = { concatUsViewModel.updateTitle(it) },
            onMessageChange = { concatUsViewModel.updateMessage(it) },
            onSendClick = { showShortToast(context, "Message sent successfully") }
        )
    }
}

@Composable
fun ContactUsScreen(
    paddingValues: PaddingValues,
    title: String,
    message: String,
    onTitleChange: (String) -> Unit,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .padding(bottom = paddingValues.calculateBottomPadding())
            .systemBarsPadding()
            .imePadding()
            .fillMaxSize()
            .padding(horizontal = SPACER16)
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.contact_us_title),
            color = MaterialTheme.colorScheme.onBackground
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(SPACER12)
                ),
            colors = textFieldColors(),
            value = title,
            onValueChange = onTitleChange,
        )
        Spacer(modifier = Modifier.height(SPACER16))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.contact_us_message),
            color = MaterialTheme.colorScheme.onBackground
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(256.dp)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(SPACER12)
                ),
            colors = textFieldColors(),
            value = message,
            onValueChange = onMessageChange,
        )
        Spacer(modifier = Modifier.height(SPACER16))
        CornerShapeButton(
            text = stringResource(R.string.send),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                focusManager.clearFocus()
                onSendClick()
            }
        )
        Text(
            color = Color.Gray,
            fontSize = 12.sp,
            text = "We've received your inquiry and will get back to you shortly. " +
                    "In the meantime, check out our knowledge base and community forums " +
                    "for advice and tips on how to use our product"
        )

    }
}

@Preview(
    "ContactUs screen",
    showBackground = true,
    device = Devices.NEXUS_5
)
@Preview(
    "ContactUs screen (dark)",
    showBackground = true,
    device = Devices.NEXUS_5,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ContactUsScreenPreview() {
    FakeShopTheme {
        ContactUsScreen(
            title = "Problem 1",
            message = "Hello, I need help with my problem",
            paddingValues = PaddingValues(0.dp),
            onSendClick = {},
            onMessageChange = {},
            onTitleChange = {},
            )
    }
}



































