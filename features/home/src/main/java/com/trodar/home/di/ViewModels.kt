package com.trodar.home.di

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trodar.home.HomeDetailViewModel
import dagger.hilt.android.EntryPointAccessors

@Composable
fun homeDetailViewModelCreator(
    id: Int
): HomeDetailViewModel{
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).homeDetailViewModelFactory()

    return  viewModel (factory = HomeDetailViewModel.provideFactory(factory, id))
}