package com.trodar.domain.catalog

import com.trodar.data.catalog.PaymentRepository
import com.trodar.model.Payment
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListPaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {

    suspend operator fun invoke(): Flow<List<Payment>> = paymentRepository.getPayments()
}