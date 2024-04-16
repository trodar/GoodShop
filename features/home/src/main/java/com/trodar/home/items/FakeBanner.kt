package com.trodar.home.items

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.trodar.model.Product
import com.trodar.theme.colors.cardColors
import com.trodar.utils.Constants.SPACER16
import com.trodar.utils.Constants.SPACER8

@Composable
fun FakeBannerItems(
    products: List<Product>,
    onProductClick: (Int) -> Unit,
) {
    LazyRow {
        item {
            Spacer(modifier = Modifier.width(SPACER8))
        }
        if (products.size > 5)
            items(products.subList(0, 5)) {
                FakeBanner(product = it, onClick = onProductClick)
            }
        else
            items(products) {
                FakeBanner(product = it, onClick = onProductClick)
            }

    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FakeBanner(
    product: Product,
    onClick: (Int) -> Unit,
) {
    val uri = Uri.parse(product.image)
    ElevatedCard(
        colors = cardColors(),
        modifier = Modifier
            .padding(end = SPACER8)
            .size(196.dp)
            .clickable { onClick(product.id) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = uri,
                contentDescription = "image"
            )
            Text(
                text = product.title.uppercase(),
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = SPACER16)
                    .basicMarquee()
            )
        }
    }
}