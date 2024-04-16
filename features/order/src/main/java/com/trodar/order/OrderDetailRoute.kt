package com.trodar.order

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.trodar.model.Order
import com.trodar.order.di.orderDetailModelCreator
import com.trodar.theme.R
import com.trodar.theme.colors.cardColors
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants
import com.trodar.utils.Constants.SPACER16
import com.trodar.utils.Constants.SPACER8
import com.trodar.utils.extensions.canGoBack
import com.trodar.utils.extensions.format
import com.trodar.utils.fetures.components.BackIconButton
import com.trodar.utils.fetures.components.CornerShapeButton
import com.trodar.utils.fetures.fragments.CenterAppBar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailRoute(
    id: Int,
    orderDetailViewModel: OrderDetailViewModel = orderDetailModelCreator(id = id),
    navController: NavHostController,
    onCatalogClick: () -> Unit,
) {
    val orderState = orderDetailViewModel.uiState.collectAsState().value
    Scaffold(

        topBar = {
            CenterAppBar(
                title = stringResource(id = R.string.purchase_details),
                navigationIconContent = {
                    BackIconButton {
                        if (navController.canGoBack)
                            navController.popBackStack()
                    }
                }
            )
        }

    ) { paddingValues ->
        OrderDetailScreen(
            order = orderState.order,
            paddingValues = paddingValues,
            onCatalogClick = onCatalogClick
        )
    }
}

@Composable
fun OrderDetailScreen(
    order: Order,
    paddingValues: PaddingValues,
    onCatalogClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = SPACER8)
    ) {
        Spacer(modifier = Modifier.height(SPACER8))
        Text(text = stringResource(id = R.string.order_detail, order.id), color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(SPACER8))


        Column(
            modifier = Modifier.padding(SPACER8),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = order.image,
                    contentDescription = "product",
                    placeholder = painterResource(id = com.trodar.utils.R.drawable.shopping_bag_96),
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Column(
                    modifier = Modifier.height(80.dp).padding(start = 4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(value = "Value: $" + order.price.format())
                    Text(value = "Store: United States")
                    Text(value = "Quantity: 1")
                }
            }
        }

        Spacer(modifier = Modifier.height(SPACER8))
        Text(text = "Voucher", color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(SPACER8))

        ElevatedCard(
            colors = cardColors(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
        ) {

            Spacer(modifier = Modifier.height(SPACER8))
            Row(modifier = Modifier.padding(SPACER8)) {
                Text(value = "74037995203390251")
                Spacer(modifier = Modifier.weight(1f))
                Icon(painter = painterResource(id = com.trodar.order.R.drawable.copy_24), contentDescription = "")
            }
            Spacer(modifier = Modifier.height(SPACER8))
        }

        Spacer(modifier = Modifier.height(SPACER16))

        TextBold(text = "Serial Number: ", boldText = "649366209", boldColor = MaterialTheme.colorScheme.onBackground)
        TextBold(text = "Pin code: ", boldText = "not available", boldColor = MaterialTheme.colorScheme.onBackground)

        Spacer(modifier = Modifier.height(SPACER16))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(SPACER16))

        val green = 0xFF249433
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBold(text = "Order status: ", boldText = "Completed", boldColor = Color(green))
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                painter = painterResource(id = com.trodar.order.R.drawable.check_circle_24),
                contentDescription = "",
                tint = Color(green),
                modifier = Modifier.size(16.dp)
            )
        }
        TextBold(text = "Payment: ", boldText = "Apple Pay", boldColor = MaterialTheme.colorScheme.onBackground)
        TextBold(text = "Order date: ", boldText = order.date.format(), boldColor = MaterialTheme.colorScheme.onBackground)

        Spacer(modifier = Modifier.height(SPACER16))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(SPACER16))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Total: ")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "$" + order.price.format(), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.weight(1f))

        CornerShapeButton(
            text = stringResource(R.string.catalog_back),
            enabled = true,
            modifier = Constants.MODIFIER.padding(bottom = SPACER16),
            onClick = onCatalogClick
        )

    }
}

@Composable
fun TextBold(
    text: String,
    boldText: String,
    boldColor: Color,
) {
    Text(fontSize = 12.sp,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onBackground,
        text = buildAnnotatedString {
            append(text)
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = boldColor)) {
                append(boldText)
            }
        }
    )
}

@Composable
fun Text(
    value: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        modifier = modifier,
        text = value,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = fontSize,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        fontWeight = fontWeight,
    )
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
fun OrderDetailScreenPreview() {

    val order = Order(
        1,
        "Majestic Mountain Graphic T-Shirt",
        234,
        "https://i.imgur.com/QkIa5tT.jpeg",
        Date(),
        "Platzi Fake Store API can be used with any type of project that needs products, users, categories, authentication, and users in JSON format. You can use this API for prototyping e-commerce and learning about how to connect to an API with the best practices."
    )

    FakeShopTheme {
        OrderDetailScreen(
            order = order,
            paddingValues = PaddingValues(),
            onCatalogClick = {}
        )
    }
}