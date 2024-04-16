package com.trodar.utils.fetures.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.trodar.theme.colors.textFieldColors
import com.trodar.utils.R
import com.trodar.utils.Constants.SPACER12

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    keyboardType: KeyboardType,
    activateError: Boolean,
    onValueChange: (String) -> Unit,
    onValidation: (String) -> Boolean,
) {
    var isValid by remember { mutableStateOf(true) }
    var initial by remember { mutableStateOf(true) }
    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = textFieldColors(),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(SPACER12)
            )
            .onFocusChanged {
                isValid = if (it.isFocused) {
                    initial = false
                    true
                } else {
                    onValidation(value) or initial
                }
            },
        isError = !isValid,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
    if (activateError && initial) {
        isValid = onValidation(value)
        initial = false
    }
    if (!isValid && !initial) {
        Text(
            text = stringResource(R.string.value_invalid),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.error,
            modifier = modifier
                .fillMaxWidth()
        )
    }
}