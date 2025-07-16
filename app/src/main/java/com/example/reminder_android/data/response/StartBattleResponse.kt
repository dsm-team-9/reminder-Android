package com.example.reminder_android.data.response

import com.google.gson.annotations.SerializedName

data class StartBattleResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("initiatorUserId") val initiatorUserId: Int,
    @SerializedName("opponentUserId") val opponentUserId: Int,
    @SerializedName("status") val status: String,
    @SerializedName("currentRound") val currentRound: Int,
    @SerializedName("initiatorScore") val initiatorScore: Int,
    @SerializedName("opponentScore") val opponentScore: Int,
)