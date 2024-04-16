package com.trodar.settings.presentation.setting

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.trodar.common.Settings
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
    val needUpdate: Boolean = false,
)
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settings: Settings,
    private val firebaseStorage: FirebaseStorage,
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        updateProfile()
    }

    fun needUpdateChange(value: Boolean){
        _uiState.update {
            it.copy(needUpdate = value)
        }
    }
    fun checkForUpdateProfile() {
        if (!uiState.value.needUpdate) return

        needUpdateChange(false)
        updateProfile()
    }

    private fun updateProfile(){
        viewModelScope.launch {
            settings.getProfile().collect {profile ->
                _uiState.update {
                    it.copy(
                        profile = profile,
                        needUpdate = false,
                    )
                }
                getImageUri(profile.photoUrl!!)
            }
        }
    }

    private fun getImageUri(ulr: String) {
        firebaseStorage.reference.child(ulr).downloadUrl.addOnSuccessListener {uri ->
            _uiState.update {
                it.copy(profileImageUri = uri)
            }
        }
    }
}