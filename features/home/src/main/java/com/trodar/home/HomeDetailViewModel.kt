package com.trodar.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.trodar.common.Result
import com.trodar.common.asResult
import com.trodar.domain.home.ProductUseCase
import com.trodar.domain.home.SelectedProductUseCase
import com.trodar.model.Product
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiDetailState(
    val isLoading: Boolean = false,
    val product: Product? = null,
)

class HomeDetailViewModel @AssistedInject constructor(
    @Assisted id: Int,
    productUseCase: ProductUseCase,
    private val selectedProductUseCase: SelectedProductUseCase,

): ViewModel() {

    private val _uiState = MutableStateFlow(UiDetailState())
    val uiState = _uiState.asStateFlow()

    val selectedProducts = selectedProductUseCase.observeProductSelected().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptySet()
    )

    init {

        viewModelScope.launch {

            productUseCase.getProductById(id)
                .asResult()
                .collect {result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.update {
                                it.copy(product = result.data, isLoading = false)
                            }
                        }
                        is Result.Error -> {
                            _uiState.update {
                                it.copy(isLoading = false)
                            }
                        }
                        is Result.Loading -> {
                            _uiState.update {
                                it.copy(isLoading = true)
                            }
                        }
                    }
                }
        }
    }

    fun toggleProductSelection(id: Int){
        viewModelScope.launch {
            selectedProductUseCase.toggleProductSelection(id)
        }
    }


    @AssistedFactory
    interface Factory{
        fun create(id: Int): HomeDetailViewModel
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