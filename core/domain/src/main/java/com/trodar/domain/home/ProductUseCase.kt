package com.trodar.domain.home

import com.trodar.data.product.ProductRepository
import com.trodar.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend fun getProductById(id: Int): Flow<Product> = productRepository.getProductById(id)

}