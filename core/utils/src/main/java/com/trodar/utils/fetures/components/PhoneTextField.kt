package com.trodar.utils.fetures.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.trodar.theme.colors.textFieldColors
import com.trodar.utils.R
import com.trodar.utils.Constants.SPACER16

@Composable
fun PhoneField(
    phone: String,
    modifier: Modifier = Modifier,
    mask: String = "000 000 00 00",
    maskNumber: Char = '0',
    prefix: String = "+7",
    activateError: Boolean,
    onPhoneChanged: (String) -> Unit,
    onValidation: (String) -> Boolean,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    var isValid by remember { mutableStateOf(true) }
    var initial by remember { mutableStateOf(true) }
    TextField(
        value = phone,
        onValueChange = { it ->
            if (it.isDigitsOnly())
                onPhoneChanged(it.take(mask.count { it == maskNumber }))
        },
        prefix = {Text(prefix)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = PhoneVisualTransformation(mask, maskNumber),
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                isValid = if (it.isFocused) {
                    initial = false
                    true
                } else {
                    onValidation(phone) or initial
                }
            },
        isError = !isValid,
        colors = textFieldColors(),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
    if (activateError && initial) {
        isValid = onValidation(phone)
        initial = false
    }
    if (!isValid && !initial) {
        Text(
            text = stringResource(R.string.value_invalid),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier
                .padding(horizontal = SPACER16)
                .fillMaxWidth()
        )
    }
}

class PhoneVisualTransformation(val mask: String, val maskNumber: Char) : VisualTransformation {

    private val maxLength = mask.count { it == maskNumber }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
           if (trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskNumber) {
                    val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneVisualTransformation) return false
        if (mask != other.mask) return false
        return maskNumber == other.maskNumber
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}

private class PhoneOffsetMapper(val mask: String, val numberChar: Char) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount) {
            if (mask[i++] != numberChar) noneDigitCount++
        }
        return offset + noneDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int =
        offset - mask.take(offset).count { it != numberChar }
}