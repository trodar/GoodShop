package com.trodar.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trodar.common.Result
import com.trodar.common.Settings
import com.trodar.common.asResult
import com.trodar.data.product.ProductRepository
import com.trodar.domain.home.SelectedProductUseCase
import com.trodar.model.Product
import com.trodar.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    val isLoading: Boolean = false,
    val profile: Profile = Profile(),
    val topProducts: List<Product> = emptyList(),
    val products: List<Product> = emptyList(),
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val settings: Settings,
    private val productRepository: ProductRepository,
    private val selectedProductUseCase: SelectedProductUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    val selectedProducts = selectedProductUseCase.observeProductSelected().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptySet()
    )

    init {
        updateProfile()
        loadCategories()
        loadProductsByCategory("women's clothing")
    }
    private fun loadCategories() {
        viewModelScope.launch {
            productRepository.getProductByCategory("men's clothing")
                .asResult()
                .collect {result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update {
                            it.copy(topProducts = result.data, isLoading = false)
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

    private fun loadProductsByCategory(categoryName: String) {
        viewModelScope.launch {
            productRepository.getProductByCategory(categoryName)
                .asResult()
                .collect {result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.update {
                                it.copy(
                                    products = result.data,
                                    isLoading = false,
                                )
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

    private fun updateProfile() {
        viewModelScope.launch {
            settings.getProfile().collect { profile ->
                _uiState.update {
                    it.copy(
                        profile = profile,
                    )
                }
            }
        }
    }
}