package com.trodar.data.profile.firebase.di

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.trodar.common.Settings
import com.trodar.data.profile.AuthenticationRepository
import com.trodar.data.profile.ProfileRepository
import com.trodar.data.profile.StorageRepository
import com.trodar.data.profile.fakeapi.AuthenticationService
import com.trodar.data.profile.firebase.FirebasePhoneAuthenticationRepository
import com.trodar.data.profile.firebase.FirebaseProfileRepository
import com.trodar.data.profile.firebase.FirebaseStorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorageInstance() = FirebaseStorage.getInstance()
//
//    @Provides
//    fun provideFirebaseDatabaseInstance() = FirebaseDatabase.getInstance()

    @Provides
    fun provideAuthenticationRepository (
        auth: FirebaseAuth,
        authenticationService: AuthenticationService,
        settings: Settings,
    ): AuthenticationRepository =
        FirebasePhoneAuthenticationRepository(auth, authenticationService, settings)

    @Provides
    fun provideStorageRepository (
        storage: FirebaseStorage
    ): StorageRepository = FirebaseStorageRepository(storage)

    @Provides
    fun provideProfileRepository (
        storageRepository: StorageRepository,
        application: Application
    ): ProfileRepository = FirebaseProfileRepository(application, storageRepository)

}