package com.trodar.common

import com.trodar.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocalSettings : Settings {

    private val _profile = MutableStateFlow(Profile())

    private val _token = MutableStateFlow("")

    override fun getProfile(): Flow<Profile> {
        return _profile.asStateFlow()
    }

    override fun setProfile(profile: Profile) {
        _profile.value = profile
    }

    override fun getToken(): Flow<String> {
        return _token.asStateFlow()
    }

    override fun setToken(token: String) {
        _token.value = token
    }
}