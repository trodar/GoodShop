package com.trodar.domain.authentification

import android.content.Context
import com.trodar.data.profile.AuthenticationRepository
import com.trodar.model.Profile
import com.trodar.model.VerificationResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {
    operator  fun invoke(
        context: Context,
        profile: Profile
    ): Flow<VerificationResult> = authenticationRepository.signUp(context, profile)
}