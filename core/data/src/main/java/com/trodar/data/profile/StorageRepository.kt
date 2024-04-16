package com.trodar.data.profile

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface StorageRepository {


    suspend fun updatePhoto(localPath: Uri, remotePath: String): Flow<String>
}