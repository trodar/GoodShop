package com.trodar.settings.presentation.contact_us

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class UiState(
    val title: String = "",
    val message: String = "",
)
@HiltViewModel
class ConcatUsViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    fun updateTitle(value: String){
        _uiState.update {
            it.copy(
                title = value
            )
        }
    }

    fun updateMessage(value: String){
        _uiState.update {
            it.copy(
                message = value
            )
        }
    }
}