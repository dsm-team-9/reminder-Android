package com.example.reminder_android.data.request

import com.google.gson.annotations.SerializedName

data class TalkAIChatCardRequest(
    @SerializedName("message") val message: String
)