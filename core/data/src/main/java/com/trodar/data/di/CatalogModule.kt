package com.trodar.data.di

import com.trodar.data.catalog.BrandMemoryRepository
import com.trodar.data.catalog.BrandRepository
import com.trodar.data.catalog.CardMemoryRepository
import com.trodar.data.catalog.CardRepository
import com.trodar.data.catalog.PaymentMemoryRepository
import com.trodar.data.catalog.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CatalogModule {

    @Binds
    @Singleton
    fun provideBrandRepository (
        memoryRepository: BrandMemoryRepository
    ): BrandRepository


    @Binds
    @Singleton
    fun providePaymentRepository(
        paymentMemoryRepository: PaymentMemoryRepository
    ): PaymentRepository

    @Binds
    @Singleton
    fun provideCardRepository(
        cardMemoryRepository: CardMemoryRepository
    ): CardRepository
}