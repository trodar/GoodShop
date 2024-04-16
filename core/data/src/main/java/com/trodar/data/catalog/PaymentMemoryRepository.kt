package com.trodar.data.catalog

import com.trodar.model.Payment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PaymentMemoryRepository @Inject constructor() : PaymentRepository {
    override suspend fun getPayments(): Flow<List<Payment>> = flow {
        listOf(
            Payment(
                1,
                "Google Pay",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRV2Ab4lwkBHU-AkhidlHxC7O3Q4U7gs5SBdnPRhBsJrqUosAAacjN8NkrZC0LnF6D_V_Y&usqp=CAU"
            ),
            Payment(
                2,
                "Google Pay",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRV2Ab4lwkBHU-AkhidlHxC7O3Q4U7gs5SBdnPRhBsJrqUosAAacjN8NkrZC0LnF6D_V_Y&usqp=CAU"
            ),
            Payment(
                3,
                "Mada pay",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Wallet_App_icon_iOS_12.png/250px-Wallet_App_icon_iOS_12.png"
            ),
            Payment(
                4,
                "Apple Pay",
                "https://www.paymentmedia.com/gallery/6463ee7768fc0apple_pay_paymentmedia.jpg"
            ),
        )
    }
}