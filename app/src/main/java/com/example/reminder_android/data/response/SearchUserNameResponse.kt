package com.example.reminder_android.data.response

import com.google.gson.annotations.SerializedName

data class SearchUserNameResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("cardCount") val cardCount: Int,
)