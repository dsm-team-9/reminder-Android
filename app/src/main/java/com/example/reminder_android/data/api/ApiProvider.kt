package com.example.reminder_android.presentations.data.api

import android.content.Context
import android.content.SharedPreferences
import com.example.reminder_android.data.api.UserApi
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import com.example.reminder_android.BuildConfig
import com.example.reminder_android.data.api.CardApi
import com.example.reminder_android.data.api.MuseumApi
import com.example.reminder_android.data.api.PvPApi

object ApiProvider {

    private const val BASE_URL = BuildConfig.BASE_URL

    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
    }


    private fun getLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(getLoggingInterceptor())
                    .addInterceptor(getTokenInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getTokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            val token = sharedPreferences.getString("token", "") ?: ""
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer $token"
                )
                .build()
            chain.proceed(request)
        }
    }

    val userApi: UserApi = getRetrofit().create(UserApi::class.java)
    val cardApi: CardApi = getRetrofit().create(CardApi::class.java)
    val pvPApi: PvPApi = getRetrofit().create(PvPApi::class.java)
    val museumApi: MuseumApi = getRetrofit().create(MuseumApi::class.java)
}
