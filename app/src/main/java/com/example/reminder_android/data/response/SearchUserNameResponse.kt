package com.example.reminder_android.data.response

import com.google.gson.annotations.SerializedName

data class SearchUserNameResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("id") val name: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
)