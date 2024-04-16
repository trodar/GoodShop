package com.trodar.data.profile

import android.content.Context
import com.trodar.model.Profile
import com.trodar.model.Verification
import com.trodar.model.VerificationResult
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun signUp(context: Context, profile: Profile): Flow<VerificationResult>
    fun signIn(context: Context, profile: Profile, verification: Verification): Flow<Boolean>
}