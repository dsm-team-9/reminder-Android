package com.example.reminder_android.data.api

import android.bluetooth.BluetoothClass
import com.example.reminder_android.data.response.FollowMuseumResponse
import com.example.reminder_android.data.response.SearchMuseumCardResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Locale.Category

interface MuseumApi {
    @POST("/reminder/museums/{museumId}/banner")
    suspend fun upLoadMuseumBannerImage(
        @Path("museumId") museumId: Long,
        @Body image: MultipartBody.Part
    )

    @GET("/reminder/museums/{userId}/cards")
    suspend fun searchMuseumCard(
        @Path("userId") userId: Int,
        @Query("category") category: BluetoothClass.Device.Major?
    ): List<SearchMuseumCardResponse>

    @GET("/reminder/museums/followings")
    suspend fun fetchFollowMuseum(): List<FollowMuseumResponse>
}