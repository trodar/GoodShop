package com.trodar.home

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.trodar.home.di.homeDetailViewModelCreator
import com.trodar.model.Product
import com.trodar.theme.R
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants.SPACER16
import com.trodar.utils.Constants.SPACER8
import com.trodar.utils.Constants.SPACER_BOTTOM
import com.trodar.utils.extensions.canGoBack
import com.trodar.utils.fetures.components.BackIconButton
import com.trodar.utils.fetures.components.CustomCircularProgressBar
import com.trodar.utils.fetures.components.ShoppingAddedIconButton
import com.trodar.utils.fetures.components.ShoppingCartIconButton
import com.trodar.utils.fetures.fragments.CenterAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDetailRoute(
    id: Int,
    navController: NavHostController,
    homeDetailViewModel: HomeDetailViewModel = homeDetailViewModelCreator(id = id),

    ) {

    val uiState by homeDetailViewModel.uiState.collectAsState()

    val selected = homeDetailViewModel.selectedProducts
        .collectAsStateWithLifecycle()
        .value
        .firstOrNull { it == id }

    Scaffold(
        topBar = {
            CenterAppBar(
                title = stringResource(R.string.product),
                navigationIconContent = {
                    BackIconButton {
                        if (navController.canGoBack)
                            navController.popBackStack()
                    }
                },
                actions = {
                    if (selected == null)
                        ShoppingCartIconButton { homeDetailViewModel.toggleProductSelection(id) }
                    else
                        ShoppingAddedIconButton { homeDetailViewModel.toggleProductSelection(id) }
                }
            )
        }
    ) { paddingValues ->

        if (uiState.isLoading) {
            CustomCircularProgressBar()
        } else {
            HomeDetail(
                paddingValues = paddingValues,
                uiState.product
            )
        }
    }
}

@Composable
fun HomeDetail(
    paddingValues: PaddingValues,
    product: Product?,
) {
    if (product == null) return

    LazyColumn(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding(), bottom = SPACER_BOTTOM)
            .padding(horizontal = SPACER8)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            AsyncImage(
                model = Uri.parse(product.image),
                contentDescription = "product",
                placeholder = painterResource(id = com.trodar.data.R.drawable.rubaha),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )

            Spacer(modifier = Modifier.height(SPACER8))

            Text(
                text = product.category,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.tertiary
            )

            Spacer(modifier = Modifier.height(SPACER16))

            Text(
                text = product.title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(SPACER16))

            Text(
                text = product.price.toString() + " USD",
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(SPACER16))

            Text(text = product.description, color = MaterialTheme.colorScheme.primary)

            Spacer(modifier = Modifier.height(SPACER16))

            Spacer(modifier = Modifier.height(SPACER16))
        }
    }
}


@Preview(
    "ProductDetailPreview screen",
    showBackground = true,
    device = Devices.NEXUS_5
)
@Preview(
    "ProductDetailPreview screen (dark)",
    showBackground = true,
    device = Devices.NEXUS_5,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ProductDetailPreview() {
    FakeShopTheme {

        HomeDetail(
            PaddingValues(),
            Product(
                1,
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                234.23f,
                "men's clothing",
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
            )
        )
    }
}
























