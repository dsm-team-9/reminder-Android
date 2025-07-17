package com.example.reminder_android.data.request

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("password") val password: String,
)