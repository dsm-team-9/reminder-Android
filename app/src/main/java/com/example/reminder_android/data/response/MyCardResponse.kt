package com.example.reminder_android.data.response

import com.google.gson.annotations.SerializedName

data class MyCardResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("content") val phoneNumber: String,
)