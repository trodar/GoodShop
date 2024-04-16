package com.trodar.common.di

import com.trodar.common.LocalSettings
import com.trodar.common.Settings
import com.trodar.model.Profile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModelModule {

    @Provides
    @Singleton
    fun getProfile(): Profile {
        return Profile()
    }

    @Provides
    @Singleton
    fun getSettings(): Settings {
        return LocalSettings()
    }
}