package com.trodar.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun FakeScreen(
    value: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello! I'm a little pink planet \uD83D\uDD2E \n $value",
            fontSize = 24.sp,
            modifier = Modifier.clickable { onClick() }
        )
    }
}