package com.trodar.data.product.fakapi

import com.trodar.data.product.ProductRepository
import com.trodar.data.product.ProductService
import com.trodar.data.model.toProduct
import com.trodar.model.Category
import com.trodar.model.Product
import com.trodar.utils.extensions.addOrRemove
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FakeApiProductRepository @Inject constructor(
    private val productService: ProductService
) : ProductRepository {

    private val selectedProducts = MutableStateFlow(setOf<Int>())

    override suspend fun getCategories(): Flow<List<Category>> {
        productService.getCategories().also { response ->
            return if (response.isSuccessful) {
                flow {
                    emit(
                        response.body()!!.map {
                            Category(it)
                        }
                    )
                }
            } else {
                throw Exception("Empty list")
            }
        }
    }

    override suspend fun getProductByCategory(categoryName: String): Flow<List<Product>> {
        productService.getProductsByCategory(categoryName).also { response ->
            return flow {
                if (response.isSuccessful) {
                    emit(
                        response.body()!!.map {
                            it.toProduct()
                        }
                    )
                } else {
                    throw Exception(response.errorBody().toString())
                }
            }
        }
    }

    override suspend fun getProductById(id: Int): Flow<Product> {
        productService.getProductById(id).also { response ->
            return flow {
                if (response.isSuccessful) {
                    emit(
                        response.body()!!.toProduct()
                    )
                } else {
                    throw Exception(response.errorBody().toString())
                }
            }
        }
    }

    override suspend fun toggleProductSelection(id: Int) {
        selectedProducts.update {
            it.addOrRemove(id)
        }
    }

    override fun observeProductSelected(): Flow<Set<Int>> = selectedProducts
}