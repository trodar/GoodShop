package com.trodar.home.items

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trodar.model.Product
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants.SPACER16
import com.trodar.utils.Constants.SPACER8
import com.trodar.utils.fetures.components.ShoppingAddedIconButton
import com.trodar.utils.fetures.components.ShoppingCartIconButton
import com.trodar.utils.fetures.fragments.CardItem

@Composable
fun FakeBestSellersItems(
    products: List<Product>,
    selectedProducts: Set<Int>,
    onClick: () -> Unit,
    onProductClick: (Int) -> Unit,
    onAddCartClick: (Int) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = SPACER8)

    ) {
        Spacer(modifier = Modifier.height(SPACER16))
        Text(
            text = "Best sellers",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1.0f))
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = "profile",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 190.dp),
        contentPadding = PaddingValues(horizontal = 4.dp),

        ) {
        items(products, key = { it.id }) { product ->
            CardItem(
                image = product.image,
                title = product.title,
                price = product.price.toString() + " USD",
                size = DpSize(250.dp, 340.dp),
                id = product.id,
                onClick = onProductClick,
                content = {
                    if (selectedProducts.contains(product.id))
                        ShoppingAddedIconButton { onAddCartClick(product.id) }
                    else
                        ShoppingCartIconButton { onAddCartClick(product.id) }
                }
            )
        }
    }
}


@Preview(
    "FakeBestSellersItems screen",
    showBackground = true,
    device = Devices.NEXUS_5
)
@Preview(
    "FakeBestSellersItems screen (dark)",
    showBackground = true,
    device = Devices.NEXUS_5,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FakeBestSellersItemsPreview() {
    FakeShopTheme {

        FakeBestSellersItems(
            listOf(
                Product(
                    1, "Classic Comfort Drawstring Joggers", 234f,
                    "Clothes", "", "https://imgur.com/1twoaDy"
                ),
                Product(
                    2, "Classic Comfort Drawstring Joggers", 234f,
                    "Clothes", "", "https://imgur.com/1twoaDy"
                ),
                Product(
                    3, "Classic Comfort Drawstring Joggers", 234f,
                    "Clothes", "", "https://imgur.com/1twoaDy"
                ),
                Product(
                    4, "Classic Comfort Drawstring Joggers", 234f,
                    "Clothes", "", "https://imgur.com/1twoaDy"
                ),
            ),
            selectedProducts = setOf(1),
            onClick = {},
            onProductClick = {},
            onAddCartClick = {},
        )
    }
}
