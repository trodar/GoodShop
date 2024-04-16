package com.trodar.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.trodar.data.order.OrderRepository
import com.trodar.model.Order
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class UiDetailState(
    val order: Order = Order(),
)

class OrderDetailViewModel @AssistedInject constructor(
    @Assisted id: Int,
    orderRepository: OrderRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(UiDetailState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    order = orderRepository.getOrderItem(id)
                )
            }

        }
    }

    @AssistedFactory
    interface Factory{
        fun create(id: Int): OrderDetailViewModel
    }

    companion object{
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }
}