package com.trodar.catalog.product_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trodar.common.Result
import com.trodar.common.asResult
import com.trodar.domain.catalog.ListCardUseCase
import com.trodar.model.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class UiState(
    val isLoading: Boolean = false,
    val cards: List<Card> = emptyList(),
)

@HiltViewModel
class CardListViewModel @Inject constructor(
    cardUseCase: ListCardUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            cardUseCase()
                .asResult()
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.update {
                                it.copy(
                                    cards = result.data,
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
}