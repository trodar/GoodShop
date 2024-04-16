package com.trodar.data.product

import com.trodar.model.Category
import com.trodar.model.Product
import kotlinx.coroutines.flow.Flow


interface ProductRepository {

    suspend fun getCategories(): Flow<List<Category>>

    suspend fun getProductByCategory(categoryName: String): Flow<List<Product>>

    suspend fun getProductById(id: Int): Flow<Product>

    suspend fun toggleProductSelection(id: Int)

    fun observeProductSelected(): Flow<Set<Int>>

}