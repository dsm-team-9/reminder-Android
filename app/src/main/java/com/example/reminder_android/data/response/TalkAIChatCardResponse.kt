package com.example.reminder_android.data.response

import com.google.gson.annotations.SerializedName

data class TalkAIChatCardResponse(
    @SerializedName("response") val content: String
)