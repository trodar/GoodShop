package com.trodar.domain.catalog

import com.trodar.data.product.ProductRepository
import com.trodar.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {
    suspend operator fun invoke(): Flow<List<Category>> = productRepository.getCategories()

}