package com.example.reminder_android.data.api

import com.example.reminder_android.data.request.TalkAIChatCardRequest
import com.example.reminder_android.data.response.TalkAIChatCardResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface MyPageApi {
    // 카드 조회
    // 카드와대화

    @POST("/api/cards/{cardId}/chat")
    suspend fun talkAIChatCard(
        @Path("cardId") cardId: Int,
        @Body request: TalkAIChatCardRequest,
    ): TalkAIChatCardResponse


}