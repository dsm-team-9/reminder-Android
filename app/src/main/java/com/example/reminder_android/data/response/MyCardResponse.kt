package com.example.reminder_android.data.response

import com.example.reminder_android.Major
import com.google.gson.annotations.SerializedName

data class MyCardResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("category") val category: Major?,
    @SerializedName("userId") val userId: Int,
    @SerializedName("museumId") val museumId: Int
)