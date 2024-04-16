package com.trodar.data.di

import com.trodar.data.product.ProductRepository
import com.trodar.data.product.fakapi.FakeApiProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface FakeApiModule {

    @Binds
    @Singleton
    fun provideCategoryRepository(
        fakeApiCategoryRepository: FakeApiProductRepository
    ):ProductRepository
}