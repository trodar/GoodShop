package com.trodar.model.response

data class ProductResponse(
    val id: Int,
    val title: String,
    val price: Float,
    val description: String,
    val category: String,
    val image: String,
)
