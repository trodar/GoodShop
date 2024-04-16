package com.trodar.common

import com.trodar.model.Profile
import kotlinx.coroutines.flow.Flow

interface Settings {

    fun getProfile(): Flow<Profile>

    fun setProfile(profile: Profile)

    fun getToken(): Flow<String>

    fun setToken(token: String)
}