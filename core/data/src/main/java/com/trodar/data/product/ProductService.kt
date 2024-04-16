package com.trodar.data.product

import com.trodar.model.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("/products/category/{categoryName}")
    suspend fun getProductsByCategory(@Path("categoryName", encoded = true) categoryName: String): Response<List<ProductResponse>>

    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<ProductResponse>
}

