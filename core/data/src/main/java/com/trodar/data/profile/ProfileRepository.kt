package com.trodar.data.profile

import com.google.firebase.auth.FirebaseUser
import com.trodar.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend  fun updateProfile(user: FirebaseUser, profile: Profile): Flow<String>
}