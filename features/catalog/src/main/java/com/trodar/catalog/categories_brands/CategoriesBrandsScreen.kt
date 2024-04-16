package com.trodar.catalog.categories_brands

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trodar.catalog.R
import com.trodar.model.Brand
import com.trodar.model.Category
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants.SPACER12
import com.trodar.utils.Constants.SPACER16
import com.trodar.utils.Constants.SPACER8
import com.trodar.utils.fetures.fragments.CardItem


@Composable
fun CategoriesBrandsScreen(
    paddingValues: PaddingValues,
    categories: List<Category>,
    brands: List<Brand>
) {

    Column {
        Box(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            Categories(categories = categories)
        }
        Spacer(modifier = Modifier.height(SPACER16))

        Box {
            Brands(brands = brands)
        }
    }

}

@Composable
fun Brands(brands: List<Brand>) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 190.dp),
        contentPadding = PaddingValues(horizontal = 4.dp),

        ) {
        items(brands, key = {it.id}) { brand ->
            CardItem(
                image = brand.image,
                title = brand.name,
                price = brand.description,
                size = DpSize(250.dp, 330.dp),
                id = 0,
                onClick = {}
            )
        }
    }
}

@Composable
fun Categories(
    categories: List<Category>
) {

    LazyRow {

        item {
            Spacer(modifier = Modifier.width(SPACER8))
            Button(
                modifier = Modifier.height(32.dp),
                shape = AbsoluteRoundedCornerShape( 50),
                contentPadding = PaddingValues(vertical = 4.dp, horizontal = SPACER16),
                onClick = {  }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sort_arrow),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(SPACER12),
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
        items(categories, key = { it.name }) { item ->

            Button(
                modifier = Modifier.height(32.dp),
                shape = AbsoluteRoundedCornerShape( 50),
                contentPadding = PaddingValues(vertical = 4.dp, horizontal = SPACER16),
                onClick = {  }
            ) {
                Text(text = item.name, fontSize = 12.sp, color = MaterialTheme.colorScheme.onPrimary)
            }
            Spacer(modifier = Modifier.width(4.dp))

        }
    }

}

@Preview(
    "CategoriesBrandsScreen screen",
    showBackground = true,
    device = Devices.NEXUS_6
)
@Preview(
    "CategoriesBrandsScreen screen (dark)",
    showBackground = true,
    device = Devices.NEXUS_6,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CategoriesBrandsScreenPreview() {
    FakeShopTheme {
        CategoriesBrandsScreen(
            paddingValues = PaddingValues(),
            categories = listOf (
                Category("Clothes"),
                Category("Electronics"),
                Category("Naya saman"),
                Category("Shoes"),
                Category("Miscellaneous"),
            ),
            brands = listOf(
                Brand(1, "Jeans", "", "Description"),
                Brand(2, "Jeans", "", "Description"),
            )
        )
    }
}
