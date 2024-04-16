package com.trodar.catalog.catalog_list

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trodar.catalog.R
import com.trodar.theme.colors.cardColors
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants.SPACER16

@Composable
fun CatalogScreen(
    onCategoriesClick: () -> Unit,
    onProductClick: () -> Unit,
    onBuyClick: () -> Unit,
    onGameClick: () -> Unit,
) {

    LazyVerticalGrid(
        modifier = Modifier
            .padding(top = SPACER16)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(SPACER16),
        columns = GridCells.FixedSize(180.dp)
    ) {

        item {
            CatalogItem(
                id = R.drawable.brands_24,
                idTxt = com.trodar.theme.R.string.catalog_categories,
                onClick = onCategoriesClick
            )
        }
        item {
            CatalogItem(
                id = R.drawable.card_24,
                idTxt = com.trodar.theme.R.string.catalog_cards,
                onClick = onProductClick
            )
        }
        item {
            CatalogItem(
                id = R.drawable.payments_24,
                idTxt = com.trodar.theme.R.string.catalog_buy,
                onClick = onBuyClick
            )
        }
        item {
            CatalogItem(
                id = R.drawable.game_24,
                idTxt = com.trodar.theme.R.string.catalog_game,
                onClick = onGameClick)
        }
    }
}


@Composable
fun CatalogItem(
    @DrawableRes id: Int,
    @StringRes idTxt: Int,
    onClick: () -> Unit,
) {

    ElevatedCard(
        modifier = Modifier
            .height(150.dp)
            .clickable { onClick() },
        colors = cardColors(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(SPACER16))

            Box(modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = id),
                    tint = MaterialTheme.colorScheme.surfaceTint,
                    contentDescription = "image"
                )
            }

            Spacer(modifier = Modifier.height(SPACER16))

            Text(
                text = stringResource(id = idTxt),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(SPACER16))
        }
    }
}

@Preview(
    "CatalogScreen screen",
    showBackground = true,
    device = Devices.NEXUS_6
)
@Preview(
    "CatalogScreen screen (dark)",
    showBackground = true,
    device = Devices.NEXUS_6,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CatalogScreenPreview() {
    FakeShopTheme {
        CatalogScreen(
            onBuyClick = {},
            onCategoriesClick = {},
            onProductClick = {},
            onGameClick = {},
        )
    }
}

