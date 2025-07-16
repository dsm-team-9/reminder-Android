package com.example.reminder_android.data.request

import com.google.gson.annotations.SerializedName

data class SelectBattleCardRequest(
    @SerializedName("cardIds") val cardIds: List<Int>
)