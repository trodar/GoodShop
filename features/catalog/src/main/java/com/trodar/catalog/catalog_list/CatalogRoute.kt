package com.trodar.catalog.catalog_list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.trodar.theme.R
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.fetures.fragments.CenterAppBar
import com.trodar.utils.fetures.showShortToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogRoute(
    onCategoriesClick: () -> Unit,
    onProductClick: () -> Unit,
) {

    val context = LocalContext.current

    Scaffold(

        topBar = {
            CenterAppBar(
                title = stringResource(R.string.catalog),
            )
        }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            CatalogScreen(
                onProductClick = onProductClick,
                onCategoriesClick = onCategoriesClick,
                onBuyClick = { showShortToast(context, "Not yet. implemented") },
                onGameClick = { showShortToast(context, "Not yet. implemented") },
            )
        }
    }
}

@Preview(
    "CatalogRoute screen",
    showBackground = true,
    device = Devices.NEXUS_6
)
@Preview(
    "CatalogRoute screen (dark)",
    showBackground = true,
    device = Devices.NEXUS_6,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CatalogRoutePreview() {
    FakeShopTheme {
        CatalogRoute(
            onCategoriesClick = {},
            onProductClick = {},
        )
    }
}

