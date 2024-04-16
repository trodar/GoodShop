package com.trodar.theme.colors

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun textFieldColors() = TextFieldDefaults.colors(
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    focusedContainerColor =  if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
    disabledContainerColor = Color.Transparent,

)

@Composable
fun cardColors() = CardDefaults.cardColors(
    containerColor = MaterialTheme.colorScheme.primaryContainer,
    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    disabledContainerColor = Color.Gray,
    disabledContentColor = Color.White,
)