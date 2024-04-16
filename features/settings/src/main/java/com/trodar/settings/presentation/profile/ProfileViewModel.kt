package com.trodar.settings.presentation.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.trodar.common.Result
import com.trodar.common.Settings
import com.trodar.common.asResult
import com.trodar.domain.profile.ProfileUpdateUseCase
import com.trodar.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class UiState(
    val profile: Profile = Profile(),
    val profileImageUri: Uri? = null,
    )

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val settings: Settings,
    private val auth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage,
    private val profileUpdateUseCase: ProfileUpdateUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            settings.getProfile().collect { profile ->
                _uiState.update {
                    it.copy(profile = profile)
                }
                getImageUri(profile.photoUrl!!)
            }
        }
    }

    fun updateProfile() {
        viewModelScope.launch {
            profileUpdateUseCase(auth.currentUser!!, _uiState.value.profile)
                .asResult()
                .collect { photoUrl ->

                    when (photoUrl) {
                        is Result.Success -> {
                            val profile = _uiState.value.profile.copy(
                                photoUrl = photoUrl.data
                            )
                            settings.setProfile(profile = profile)
                        }

                        is Result.Error -> {
                            //errorMessageChange(photoUrl.exception.message ?: "")
                        }

                        is Result.Loading -> {
                            //isLoadingChange(true)
                        }
                    }
                }
        }
    }

    fun profileChange(profile: Profile) = _uiState.update { it.copy(profile = profile) }

    fun uriChange(uri: Uri?) {
        _uiState.update {
            it.copy(
                profileImageUri = uri,
                profile = it.profile.copy(photoUrl = uri?.toString())
            )
        }

    }

    private fun getImageUri(ulr: String) {

        firebaseStorage.reference.child(ulr).downloadUrl.addOnSuccessListener { uri ->
            _uiState.update {
                it.copy(profileImageUri = uri)
            }
        }
    }

}