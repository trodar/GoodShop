package com.trodar.utils.fetures.components

import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.trodar.theme.R


@Composable
fun BackIconButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = stringResource(R.string.back),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun FindIconButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = stringResource(R.string.back),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ShoppingCartIconButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Rounded.ShoppingCart,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ShoppingAddedIconButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Rounded.Done,
            contentDescription = "",
            tint = Color.Green
        )
    }
}

//@Composable
//fun AddIconButton(onClick: () -> Unit) {
//    IconButton(onClick) {
//        Icon(
//            imageVector = Icons.Rounded.Add,
//            contentDescription = stringResource(com.trodar.theme.R.string.add),
//            tint = MaterialTheme.colorScheme.onBackground
//        )
//    }
//}

@Composable
fun CornerShapeButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val cornerShape = 30
    Button(
        modifier = modifier,
        enabled = enabled,
        shape = AbsoluteRoundedCornerShape(
            topLeftPercent = cornerShape,
            topRightPercent = cornerShape,
            bottomLeftPercent = cornerShape,
            bottomRightPercent = cornerShape
        ),
        onClick = onClick
    ) {
        Text(text = text)
    }
}
















