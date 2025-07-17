package com.example.reminder_android.presentation.feature.main.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.reminder_android.data.response.MyCardResponse
import com.example.reminder_android.presentations.data.api.ApiProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {
    var chatCardList = listOf<MyCardResponse>()

    fun fetchMyCard() {
        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
                ApiProvider.cardApi.fetchMyCard(
                    category = null
                )
            }.onSuccess {
                chatCardList = it
            }.onFailure {
                Log.d("TEST", it.toString())
            }
        }
    }
}