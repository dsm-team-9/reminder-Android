package com.example.reminder_android.data.api

import com.example.reminder_android.Major
import com.example.reminder_android.data.request.ChangeContentCardRequest
import com.example.reminder_android.data.request.CreateCardRequest
import com.example.reminder_android.data.request.TalkAIChatCardRequest
import com.example.reminder_android.data.response.MyCardResponse
import com.example.reminder_android.data.response.TalkAIChatCardResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CardApi {
    //카드생성
    //카드내용수정

    @GET("/card")
    suspend fun fetchMyCard(
        @Query("category") category: Major,
    ): List<MyCardResponse>

    @PATCH("/api/cards/{cardId}")
    suspend fun changeContentCard(
        @Path("cardId") cardId: Int,
        @Body request: ChangeContentCardRequest,
    )

    @POST("/api/cards")
    suspend fun createCard(
        @Body request: CreateCardRequest,
    )

    @POST("/api/cards/{cardId}/chat")
    suspend fun talkAIChatCard(
        @Path("cardId") cardId: Int,
        @Body request: TalkAIChatCardRequest,
    ): TalkAIChatCardResponse
}