package com.example.reminder_android.data.api

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SocialApi {
    // 팔로우 목록 조회
    // 이름으로 사용자 검색
    // 팔로우
    // 언팔로우

    @POST("/api/users/{id}/follow")
    fun followUser(
        @Path("id") userId: Long,
    )

    @DELETE("/api/users/{id}/follow")
    fun unFollowUser(
        @Path("id") userId: Long,
    )

    @GET("/api/users/search")
    fun searchUserName(
        @Query("name") name: String,
    )


}