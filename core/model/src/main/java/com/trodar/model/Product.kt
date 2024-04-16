package com.trodar.model


data class Product (
    val id: Int,
    val title: String,
    val price: Float,
    val category: String,
    val description: String,
    val image: String
)