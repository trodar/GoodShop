package com.trodar.data.di

import com.trodar.data.order.FakeOrderRepository
import com.trodar.data.order.OrderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface OrderModule {

    @Binds
    @Singleton
    fun provideOrderRepository (
        fakeOrderRepository: FakeOrderRepository
    ): OrderRepository
}