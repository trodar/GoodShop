package com.trodar.domain.profile

import com.google.firebase.auth.FirebaseUser
import com.trodar.data.profile.ProfileRepository
import com.trodar.model.Profile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUpdateUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(user: FirebaseUser, profile: Profile): Flow<String>  {
        return profileRepository.updateProfile(user, profile)
    }
}