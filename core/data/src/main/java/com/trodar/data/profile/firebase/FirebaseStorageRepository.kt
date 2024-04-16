package com.trodar.data.profile.firebase

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.trodar.data.profile.StorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseStorageRepository @Inject constructor(
    private val storage: FirebaseStorage
): StorageRepository {
    override suspend fun updatePhoto(localPath: Uri, remotePath: String): Flow<String> = flow {

        val riversRef =  storage.reference.child(remotePath)
        val uploadTask = riversRef.putFile(localPath).apply {  }.await()
        emit(uploadTask.metadata?.path ?: "")
    }
}