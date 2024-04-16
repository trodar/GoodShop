package com.trodar.authentication.domain

import com.trodar.model.Profile
import com.trodar.model.Verification

sealed interface AuthenticationUiState {
    val isLoading: Boolean
    val errorMessage: String
    val profile: Profile

    data class Registration(
        override val isLoading: Boolean,
        override val errorMessage: String,
        override val profile: Profile,
    ) : AuthenticationUiState

    data class OtpSent(
        val verification: Verification,
        override val isLoading: Boolean,
        override val errorMessage: String,
        override val profile: Profile,
    ) : AuthenticationUiState
}

internal data class AuthenticationViewModelState(
    val isLoading: Boolean = false,
    val profile: Profile = Profile(),
    val otpSent: Boolean = false,
    val errorMessage: String = "",
    val verification: Verification = Verification(),
) {
    fun toUiState(): AuthenticationUiState =
        if (!otpSent) {
            AuthenticationUiState.Registration(
                isLoading = isLoading,
                errorMessage = errorMessage,
                profile = profile,
            )
        } else {
            AuthenticationUiState.OtpSent(
                isLoading = isLoading,
                profile = profile,
                errorMessage = errorMessage,
                verification = verification,
            )
        }
}