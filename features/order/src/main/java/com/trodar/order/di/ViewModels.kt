package com.trodar.order.di

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trodar.order.OrderDetailViewModel
import dagger.hilt.android.EntryPointAccessors

@Composable
fun orderDetailModelCreator(
    id: Int
) : OrderDetailViewModel{
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).orderDetailViewModelFactory()

    return viewModel(
        factory = OrderDetailViewModel.provideFactory(factory, id)
    )
}
