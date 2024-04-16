package com.trodar.catalog.categories_brands

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trodar.common.Result
import com.trodar.common.asResult
import com.trodar.domain.catalog.GetCategoriesUseCase
import com.trodar.domain.catalog.ListBrandsUseCase
import com.trodar.model.Brand
import com.trodar.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class UiState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val brands: List<Brand> = emptyList(),
)
@HiltViewModel
class CategoriesBrandsViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val listBrandsUseCase: ListBrandsUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            getCategoriesUseCase()
                .asResult()
                .collect {result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.update {
                                it.copy(
                                    categories = result.data,
                                    brands = listBrandsUseCase(),
                                    isLoading = false)
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
}