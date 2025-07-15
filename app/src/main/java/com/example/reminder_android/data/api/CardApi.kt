package com.example.reminder_android.data.api

import retrofit2.http.PATCH

interface CardApi {
    //카드생성
    //카드내용수정

    @PATCH("")
    suspend fun changeContentCard()

}