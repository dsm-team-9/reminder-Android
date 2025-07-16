package com.example.reminder_android.data.api

import com.example.reminder_android.data.request.SignInRequest
import com.example.reminder_android.data.request.SignUpRequest
import com.example.reminder_android.data.response.SearchFollowingUserResponse
import com.example.reminder_android.data.response.SearchUserNameResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @POST("/auth/signup")
    suspend fun signUp(
        @Body request: SignUpRequest,
    )

    @POST("/auth/login")
    suspend fun signIn(
        @Body request: SignInRequest,
    )

    @PATCH("/auth/pvp-status")
    suspend fun enabledPvpStatus(
        @Query("enabled") enabled: Boolean,
    ): Boolean

    @GET("/api/users/search")
    suspend fun searchUserName(
        @Query("name") name: String,
    ): List<SearchUserNameResponse>

    @GET("/auth/following")
    suspend fun searchFollowingUser(): List<SearchFollowingUserResponse>

    @POST("/api/users/{id}/follow")
    suspend fun followUser(
        @Path("id") userId: Long,
    )

    @DELETE("/api/users/{id}/follow")
    suspend fun unFollowUser(
        @Path("id") userId: Long,
    )
}