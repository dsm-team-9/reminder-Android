package com.example.reminder_android.data.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("expiredAt") val expiredAt: Long
)