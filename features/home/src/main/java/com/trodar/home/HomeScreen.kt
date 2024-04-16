package com.trodar.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.trodar.home.items.FakeBannerItems
import com.trodar.home.items.FakeBestSellersItems
import com.trodar.model.Product
import com.trodar.utils.Constants.SPACER_BOTTOM


@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    bannerProducts: List<Product>,
    products: List<Product>,
    selectedProduct: Set<Int>,
    onProductClick: (Int) -> Unit,
    onAddCartClick: (Int) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(bottom = SPACER_BOTTOM)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),

    ) {

        FakeBannerItems(products = bannerProducts, onProductClick = onProductClick)

        FakeBestSellersItems(
            products = products,
            selectedProducts = selectedProduct,
            onClick = {},
            onProductClick = onProductClick,
            onAddCartClick = onAddCartClick,
        )
    }
}