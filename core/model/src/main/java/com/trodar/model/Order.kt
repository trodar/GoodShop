package com.trodar.model

import java.util.Date

data class Order(
    val id: Int = 0,
    val title: String = "",
    val price: Int = 0,
    val image: String = "",
    val date: Date = Date(),
    val description: String = "",
)
