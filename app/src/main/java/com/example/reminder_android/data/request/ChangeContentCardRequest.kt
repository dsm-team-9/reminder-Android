package com.example.reminder_android.data.request

import com.google.gson.annotations.SerializedName

data class ChangeContentCardRequest(
    @SerializedName("content") val content: String,
)