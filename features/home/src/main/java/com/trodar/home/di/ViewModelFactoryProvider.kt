package com.trodar.home.di

import com.trodar.home.HomeDetailViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {

    fun homeDetailViewModelFactory(): HomeDetailViewModel.Factory

}