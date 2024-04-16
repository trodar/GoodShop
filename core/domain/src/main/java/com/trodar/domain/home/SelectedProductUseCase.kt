package com.trodar.domain.home

import com.trodar.data.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SelectedProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    suspend fun toggleProductSelection(id: Int) = productRepository.toggleProductSelection(id)

    fun observeProductSelected(): Flow<Set<Int>> = productRepository.observeProductSelected()

}