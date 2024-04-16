package com.trodar.authentication.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.trodar.authentication.domain.AuthenticationViewModelState
import com.trodar.common.Result
import com.trodar.common.Settings
import com.trodar.common.asResult
import com.trodar.domain.authentification.SignInUseCase
import com.trodar.domain.authentification.SignUpUseCase
import com.trodar.model.Profile
import com.trodar.model.Verification
import com.trodar.model.VerificationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val settings: Settings,
    private val auth: FirebaseAuth,
    private val singInUseCase: SignInUseCase,
    private val singUpUseCase: SignUpUseCase,
) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        AuthenticationViewModelState()
    )
    val uiState = viewModelState
        .map(AuthenticationViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        viewModelScope.launch {
            settings.getProfile().collect { profile ->
                profileChange(profile)
            }
        }
    }

    fun signUp(context: Context) {
        viewModelScope.launch {
            singUpUseCase(
                context = context,
                profile = viewModelState.value.profile
            )
                .asResult()
                .collect { signUpResult ->
                    when (signUpResult) {
                        is Result.Success -> {
                            isLoadingChange(false)

                            when (signUpResult.data) {
                                is VerificationResult.Failure -> {
                                    errorMessageChange(signUpResult.data.value)
                                }

                                is VerificationResult.Success -> {
                                    val verification = viewModelState.value.verification
                                    verificationChange(
                                        verification.copy(verificationId = signUpResult.data.value)
                                    )
                                    otpSend()
                                }
                            }
                        }

                        is Result.Error -> {
                            isLoadingChange(false)
                            errorMessageChange(signUpResult.exception.message ?: "")
                        }

                        is Result.Loading -> {
                            isLoadingChange(true)
                        }
                    }
                }
        }
    }

    fun signIn(context: Context, onClick: () -> Unit) {
        viewModelScope.launch {
            singInUseCase(
                context = context,
                profile = viewModelState.value.profile,
                verification = viewModelState.value.verification
            )
                .asResult()
                .collect { signInResult ->
                    when (signInResult) {
                        is Result.Success -> {
                            isLoadingChange(false)

                            val fireUser = auth.currentUser!!

                            val profile = Profile(
                                photoUrl = fireUser.photoUrl?.path,
                                user = fireUser.displayName ?: "",
                                phone = fireUser.phoneNumber ?: "",
                                email = fireUser.email ?: "",
                                advt = true,
                            )
                            settings.setProfile(profile)

                            onClick()
                        }

                        is Result.Error -> {
                            isLoadingChange(false)
                            errorMessageChange(signInResult.exception.message ?: "")
                        }

                        is Result.Loading -> {
                            isLoadingChange(true)
                        }
                    }
                }
        }
    }

    private fun otpSend() = viewModelState.update { it.copy(otpSent = true) }

    fun profileChange(profile: Profile) = viewModelState.update { it.copy(profile = profile) }

    fun verificationChange(value: Verification) =
        viewModelState.update { it.copy(verification = value) }

    fun errorMessageChange(value: String) =
        viewModelState.update { it.copy(errorMessage = value) }

    private fun isLoadingChange(value: Boolean) =
        viewModelState.update { it.copy(isLoading = value) }
}