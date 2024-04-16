package com.trodar.domain.authentification

import android.content.Context
import com.trodar.data.profile.AuthenticationRepository
import com.trodar.model.Profile
import com.trodar.model.Verification
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {

    operator fun invoke(
        context: Context,
        profile: Profile,
        verification: Verification
    ): Flow<Boolean> = authenticationRepository.signIn(context, profile, verification)
}