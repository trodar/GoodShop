package com.trodar.utils.fetures.fragments

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.trodar.theme.colors.cardColors
import com.trodar.theme.themes.FakeShopTheme
import com.trodar.utils.Constants
import com.trodar.utils.R
import com.trodar.utils.fetures.components.ShoppingCartIconButton

@Composable
fun CardItem(
    image: String,
    title: String,
    price: String,
    size: DpSize = DpSize(250.dp, 250.dp),
    id: Int,
    onClick: (Int) -> Unit,
    content: (@Composable () -> Unit)? = null
) {
    val modifier = if (id > 0) Modifier.clickable { onClick(id) } else Modifier
    ElevatedCard(
        colors = cardColors(),
        modifier = modifier
            .size(size)
            .padding(4.dp)
    ) {
        Column {
            AsyncImage(
                model = Uri.parse(image),
                contentDescription = "product",
                placeholder = painterResource(id = R.drawable.shopping_bag_96),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp))
            )
            Spacer(modifier = Modifier.height(Constants.SPACER8))
            Text(
                text = title,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = price,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (content != null) {
                    content()
                }
            }
        }
    }
}

@Preview(
    "CardItemPreview screen",
    showBackground = true,
    device = Devices.NEXUS_5
)
@Preview(
    "CardItemPreview screen (dark)",
    showBackground = true,
    device = Devices.NEXUS_5,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CardItemPreview() {
    FakeShopTheme {

        CardItem(
            image = "",
            title = "To ensure the animation",
            price = "123.12",
            id = 1,
            size = DpSize(250.dp, 340.dp),
            onClick = {},
            content = { ShoppingCartIconButton {  } }
        )
    }
}