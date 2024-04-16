package com.trodar.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trodar.data.order.OrderRepository
import com.trodar.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class  UiState(
    val orders: List<Order> = emptyList()
)
@HiltViewModel
class OrderViewModel @Inject constructor(
    orderRepository: OrderRepository

) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(orders = orderRepository.getOrders())
            }
        }
    }
}


