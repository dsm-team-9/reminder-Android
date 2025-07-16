package com.example.reminder_android.data.api

import com.example.reminder_android.data.request.SelectBattleCardRequest
import com.example.reminder_android.data.response.BattleResultResponse
import com.example.reminder_android.data.response.PlayRoundCountResponse
import com.example.reminder_android.data.response.StartBattleResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PvPApi {
    @POST("/api/pvp/{battleId}/play-round")
    suspend fun playRoundCount(
        @Path("battleId") battleId: Int,
    ): PlayRoundCountResponse

    @POST("/api/pvp/initiate/{opponentUserId}")
    suspend fun startBattle(
        @Path("opponentUserId") opponentUserId: Int,
    ): StartBattleResponse

    @POST("/api/pvp/cards")
    suspend fun selectBattleCard(
        @Body request: SelectBattleCardRequest,
    )

    @GET("/api/pvp/can-initiate/{opponentUserId}")
    suspend fun appearPlayBattle(
        @Path("opponentUserId") opponentUserId: Int
    ): Boolean

    @GET("/api/pvp/battles/latest/result")
    suspend fun fetchBattleResult(): BattleResultResponse

    @GET("/api/pvp/active")
    suspend fun fetchMyActiveBattle(): Boolean

}