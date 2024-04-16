package com.trodar.data.profile.fakeapi

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationService {
    @POST("auth/login")
    @Headers("No-Authentication:true")
    suspend fun login(@Body authRequest: AuthRequest): Response<AuthResponse>
}

data class AuthRequest(
    val username: String,
    val password: String
)

data class AuthResponse(
    @SerializedName("token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)