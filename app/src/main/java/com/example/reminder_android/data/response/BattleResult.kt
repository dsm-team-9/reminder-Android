package com.example.reminder_android.data.response

import com.google.gson.annotations.SerializedName

data class BattleResultResponse(
    @SerializedName("totalWins") val totalWins: Int,
    @SerializedName("totalLosses") val totalLosses: Int,
    @SerializedName("winRate") val winRate: Int,
    @SerializedName("roundResults") val roundResults: List<RoundResult>
)

data class RoundResult(
    @SerializedName("roundNumber") val roundNumber: Int,
    @SerializedName("userCardName") val userCardName: String,
    @SerializedName("result") val result: String,
    @SerializedName("feedback") val feedback: String
)