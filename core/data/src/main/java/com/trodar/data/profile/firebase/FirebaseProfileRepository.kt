package com.trodar.data.profile.firebase

import android.content.Context
import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import com.trodar.common.Result.Error
import com.trodar.common.Result.Loading
import com.trodar.common.Result.Success
import com.trodar.common.asResult
import com.trodar.data.profile.ProfileRepository
import com.trodar.data.profile.StorageRepository
import com.trodar.model.Profile
import com.trodar.utils.extensions.getImageExtension
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseProfileRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val storageRepository: StorageRepository,
) : ProfileRepository {

    private val userPhotoPath = "user/%s/image/profile.%s"

    override suspend fun updateProfile(user: FirebaseUser, profile: Profile): Flow<String> = flow {

        val localPhotoUri = Uri.parse(profile.photoUrl)
        val remotePath = String.format(
            userPhotoPath,
            user.uid,
            localPhotoUri.getImageExtension(context = context)
        )

        if (localPhotoUri.toString() == remotePath) {
            val profileUpdates = userProfileChangeRequest {
                displayName = profile.user
            }
            user.updateProfile(profileUpdates).apply { }.await()
            user.verifyBeforeUpdateEmail(profile.email)
            emit(profile.photoUrl!!)

        } else {
            storageRepository.updatePhoto(localPhotoUri, remotePath)
                .asResult()
                .collect { uploadResult ->
                    when (uploadResult) {
                        is Success -> {
                            val profileUpdates = userProfileChangeRequest {
                                displayName = profile.user
                                photoUri = Uri.parse(uploadResult.data)
                            }
                            user.updateProfile(profileUpdates).apply { }.await()
                            user.verifyBeforeUpdateEmail(profile.email)
                            emit(uploadResult.data)
                        }

                        is Error -> {
                            throw (uploadResult.exception)
                        }

                        is Loading -> {

                        }
                    }
                }
        }
    }
}


