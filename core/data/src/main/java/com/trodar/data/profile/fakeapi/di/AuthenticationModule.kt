package com.trodar.data.profile.fakeapi.di

import com.trodar.data.profile.fakeapi.AuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Singleton
    @Provides
    fun loginServiceProvide (retrofit: Retrofit): AuthenticationService {
        return  retrofit.create(AuthenticationService::class.java)
    }
}