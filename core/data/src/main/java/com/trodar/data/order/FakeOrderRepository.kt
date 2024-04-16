package com.trodar.data.order

import com.trodar.model.Order
import java.util.Calendar
import javax.inject.Inject


class FakeOrderRepository @Inject constructor(): OrderRepository {
    override suspend fun getOrders(): List<Order> {
        return orderList()
    }

    override suspend fun getOrderItem(id: Int): Order {
        return orderList().first { it.id == id }
    }
}

private fun orderList(): List<Order> {

    val datetime = Calendar.getInstance()
    val timeToday = datetime.time

    datetime.add(Calendar.MONTH, -1)
    val timeMinusMonth = datetime.time

    datetime.add(Calendar.MONTH, -2)
    val timeMinus3Month = datetime.time

    return listOf(
        Order(
            1,
            "Majestic Mountain Graphic T-Shirt",
            234,
            "https://i.imgur.com/QkIa5tT.jpeg",
            timeToday,
            "Platzi Fake Store API can be used with any type of project that needs products, users, categories, authentication, and users in JSON format. You can use this API for prototyping e-commerce and learning about how to connect to an API with the best practices."
        ),
        Order(
            2,
            "Classic Red Pullover Hoodie",
            234,
            "https://i.imgur.com/1twoaDy.jpeg",
            timeToday,
            "Platzi Fake Store API can be used with any type of project that needs products, users, categories, authentication, and users in JSON format. You can use this API for prototyping e-commerce and learning about how to connect to an API with the best practices."
        ),
        Order(
            3,
            "Classic Heather Gray Hoodie",
            234,
            "https://i.imgur.com/cHddUCu.jpeg",
            timeToday,
            "Platzi Fake Store API can be used with any type of project that needs products, users, categories, authentication, and users in JSON format. You can use this API for prototyping e-commerce and learning about how to connect to an API with the best practices."
        ),

        Order(
            4,
            "Classic Grey Hooded Sweatshirt",
            234,
            "https://i.imgur.com/R2PN9Wq.jpeg",
            timeMinusMonth,
            "Platzi Fake Store API can be used with any type of project that needs products, users, categories, authentication, and users in JSON format. You can use this API for prototyping e-commerce and learning about how to connect to an API with the best practices."
        ),
        Order(
            5,
            "Classic Black Hooded Sweatshirt",
            234,
            "https://i.imgur.com/9LFjwpI.jpeg",
            timeMinusMonth,
            "Platzi Fake Store API can be used with any type of project that needs products, users, categories, authentication, and users in JSON format. You can use this API for prototyping e-commerce and learning about how to connect to an API with the best practices."
        ),

        Order(
            6,
            "Classic Comfort Fit Joggers",
            234,
            "https://i.imgur.com/cSytoSD.jpeg",
            timeMinus3Month,
            "Platzi Fake Store API can be used with any type of project that needs products, users, categories, authentication, and users in JSON format. You can use this API for prototyping e-commerce and learning about how to connect to an API with the best practices."
        ),
    )
}