package com.example.reminder_android.data.response

import com.google.gson.annotations.SerializedName

data class FollowMuseumResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("username") val username: String,
    @SerializedName("bannerUrl") val bannerUrl: String,
    @SerializedName("cardCount") val cardCount: Int,
)