package com.trodar.model

data class Verification(
    val otpNumber: String = "",
    val verificationId: String = "",
)

sealed interface VerificationResult {
    val value: String

    data class Success(
        override val value: String
    ) : VerificationResult

    data class Failure(
        override val value: String
    ) : VerificationResult
}