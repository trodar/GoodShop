package com.trodar.data.model

import com.trodar.model.Product
import com.trodar.model.response.ProductResponse

fun ProductResponse.toProduct() = Product(
    id = id,
    title = title,
    price = price,
    category = category,
    description = description,
    image = image,
)