package com.example.reminder_android.data.api

import com.example.reminder_android.data.request.SignInRequest
import com.example.reminder_android.data.request.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/auth/signup")
    suspend fun signUp(
        @Body request: SignUpRequest,
    )

    @POST("/api/auth/login")
    suspend fun signIn(
        @Body request: SignInRequest,
    )
}