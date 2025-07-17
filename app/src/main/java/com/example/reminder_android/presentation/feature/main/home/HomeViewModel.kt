package com.example.reminder_android.presentation.feature.main.home

import androidx.lifecycle.ViewModel
import com.example.reminder_android.data.response.FollowMuseumResponse
import com.example.reminder_android.data.response.SearchMuseumCardResponse
import com.example.reminder_android.presentations.data.api.ApiProvider

class HomeViewModel: ViewModel() {
    var followMuseumList: List<FollowMuseumResponse> = listOf()
    var cardList: List<SearchMuseumCardResponse> = listOf()

    suspend fun searchMuseumCard(userId : Int) {
        runCatching {
            ApiProvider.museumApi.searchMuseumCard(
                userId = userId,
                category = null
            )
        }.onSuccess {
            cardList = it
        }
    }

    suspend fun fetchFollowMuseum() {
        runCatching {
            ApiProvider.museumApi.fetchFollowMuseum()
        }.onSuccess {
            followMuseumList = it
        }
    }
}