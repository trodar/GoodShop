package com.trodar.utils.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun Date.format(pattern: String = "dd MMMM yyyy, HH:mm a"): String {
    val formatter = SimpleDateFormat(pattern)

    return formatter.format(this)
}