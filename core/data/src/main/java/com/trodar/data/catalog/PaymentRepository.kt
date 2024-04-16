package com.trodar.data.catalog

import com.trodar.model.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {

    suspend fun getPayments(): Flow<List<Payment>>
}