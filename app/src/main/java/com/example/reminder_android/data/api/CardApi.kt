package com.example.reminder_android.data.api

import com.example.reminder_android.data.request.ChangeContentCardRequest
import com.example.reminder_android.data.request.CreateCardRequest
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface CardApi {
    //카드생성
    //카드내용수정

    @PATCH("/api/cards/{cardId}")
    suspend fun changeContentCard(
        @Path("cardId") cardId: Int,
        @Body request: ChangeContentCardRequest,
    )

    @POST("/api/cards")
    suspend fun createCard(
        @Body request: CreateCardRequest,
    )
}