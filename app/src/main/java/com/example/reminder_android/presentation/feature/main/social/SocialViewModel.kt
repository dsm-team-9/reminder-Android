package com.example.reminder_android.presentation.feature.main.social

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.reminder_android.data.response.SearchFollowingUserResponse
import com.example.reminder_android.data.response.SearchUserNameResponse
import com.example.reminder_android.presentations.data.api.ApiProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SocialViewModel : ViewModel() {
    private val _searchResults = MutableStateFlow<List<SearchUserNameResponse>>(emptyList())
    val searchResults: StateFlow<List<SearchUserNameResponse>> = _searchResults

    var followList = listOf<SearchFollowingUserResponse>()

    suspend fun searchUser(query: String) {
        if (query.isBlank()) {
            _searchResults.value = emptyList()
            return
        }
        runCatching {
            ApiProvider.userApi.searchUserName(query)
        }.onSuccess {
            _searchResults.value = it
        }.onFailure {
            Log.d("TEST", it.toString())
        }
    }

    suspend fun fetchFollowUser() {
        runCatching {
            ApiProvider.userApi.searchFollowingUser()
        }.onSuccess {
            followList = it
        }.onFailure {
            Log.d("TEST", it.toString())
        }
    }
}