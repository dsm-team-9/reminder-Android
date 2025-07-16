package com.example.reminder_android.data.request

import com.example.reminder_android.Major
import com.google.gson.annotations.SerializedName
import java.io.Serial

data class CreateCardRequest(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("category") val category: Major,
)