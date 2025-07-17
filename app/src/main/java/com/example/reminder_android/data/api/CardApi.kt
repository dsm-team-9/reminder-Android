package com.example.reminder_android.data.api

import android.bluetooth.BluetoothClass
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

    @GET("/reminder/card")
    suspend fun fetchMyCard(
        @Query("category") category: BluetoothClass.Device.Major?,
    ): List<MyCardResponse>

    @PATCH("/reminder/cards/{cardId}")
    suspend fun changeContentCard(
        @Path("cardId") cardId: Int,
        @Body request: ChangeContentCardRequest,
    )

    @POST("/reminder/card")
    suspend fun createCard(
        @Body request: CreateCardRequest,
    )

    @POST("/reminder/cards/{cardId}/chat")
    suspend fun talkAIChatCard(
        @Path("cardId") cardId: Int,
        @Body request: TalkAIChatCardRequest,
    ): TalkAIChatCardResponse
}