package com.example.reminder_android.data.api

import com.example.reminder_android.data.request.SignInRequest
import com.example.reminder_android.data.request.SignUpRequest
import com.example.reminder_android.data.response.SearchFollowingUserResponse
import com.example.reminder_android.data.response.SearchUserNameResponse
import com.example.reminder_android.data.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @POST("/reminder/auth/signup")
    suspend fun signUp(
        @Body request: SignUpRequest,
    )

    @POST("/reminder/auth/login")
    suspend fun signIn(
        @Body request: SignInRequest,
    ): SignInResponse

    @PATCH("/reminder/auth/pvp-status")
    suspend fun enabledPvpStatus(
        @Query("enabled") enabled: Boolean,
    ): Boolean

    @GET("/reminder/auth/search")
    suspend fun searchUserName(
        @Query("name") name: String,
    ): List<SearchUserNameResponse>

    @GET("/reminder/auth/following")
    suspend fun searchFollowingUser(): List<SearchFollowingUserResponse>

    @POST("/reminder/auth/{id}/follow")
    suspend fun followUser(
        @Path("id") userId: Int,
    )

    @DELETE("/reminder/auth/{id}/follow")
    suspend fun unFollowUser(
        @Path("id") userId: Int,
    )
}