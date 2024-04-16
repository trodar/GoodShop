package com.trodar.order.di

import com.trodar.order.OrderDetailViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {

    fun orderDetailViewModelFactory(): OrderDetailViewModel.Factory

}