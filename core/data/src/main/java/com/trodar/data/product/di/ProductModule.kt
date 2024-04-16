package com.trodar.data.product.di

import com.trodar.data.product.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ProductModule {

    @Singleton
    @Provides
    fun productServiceProvide(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }
}