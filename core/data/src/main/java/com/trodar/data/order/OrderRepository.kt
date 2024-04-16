package com.trodar.data.order

import com.trodar.model.Order

interface OrderRepository {
    suspend fun getOrders(): List<Order>

    suspend fun getOrderItem(id: Int): Order
}