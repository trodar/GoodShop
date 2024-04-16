package com.trodar.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.trodar.model.Order
import com.trodar.theme.R
import com.trodar.theme.colors.cardColors
import com.trodar.utils.Constants
import com.trodar.utils.Constants.SPACER_BOTTOM
import com.trodar.utils.fetures.fragments.CenterAppBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderRoute(
    orderViewModel: OrderViewModel = hiltViewModel(),
    onClick: (Int) -> Unit
) {

    val orderState = orderViewModel.uiState.collectAsState()
    Scaffold(

        topBar = {
            CenterAppBar(
                title = stringResource(id = R.string.order),
            )
        }
    ) { paddingValues ->
        OrderScreen(
            orderState.value.orders,
            paddingValues,
            onClick = onClick,
        )
    }
}

@Composable
fun OrderScreen(
    orders: List<Order>,
    paddingValues: PaddingValues,
    onClick: (Int) -> Unit,
) {
    val orderGroup = orders.groupBy { it.date }.toSortedMap(compareByDescending { it })
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = Constants.SPACER8)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = SPACER_BOTTOM)
    ) {

        orderGroup.forEach { groupItem ->
            item {
                Spacer(modifier = Modifier.height(Constants.SPACER8))
                val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.US)
                val txt = if (formatter.format(groupItem.key) == formatter.format(Date())) "Today" else formatter.format(groupItem.key)
                Text(text = txt)
                Spacer(modifier = Modifier.height(Constants.SPACER16))
            }

            items(groupItem.value) { order ->
                OrderItem(order = order, onClick = onClick)
                Spacer(modifier = Modifier.height(Constants.SPACER8))
            }
        }
    }
}

@Composable
fun OrderItem(
    order: Order,
    onClick: (Int) -> Unit,
) {
    ElevatedCard(
        colors = cardColors(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = order.image,
                contentDescription = "image",
                modifier = Modifier
                    .padding(10.dp)
                    .size(60.dp)
                    .clip(RoundedCornerShape(5.dp)),
            )
            Column(
                modifier = Modifier
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.store2dot, order.title),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = stringResource(id = R.string.value2dot, order.price, "USD"),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { onClick(order.id) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = "detail",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}