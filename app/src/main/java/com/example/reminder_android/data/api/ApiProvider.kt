package com.example.reminder_android.presentations.data.api

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
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
import androidx.core.content.edit

object ApiProvider {

    private const val BASE_URL = BuildConfig.BASE_URL

    private lateinit var sharedPreferences: SharedPreferences

    lateinit var userApi: UserApi
    lateinit var cardApi: CardApi
    lateinit var pvPApi: PvPApi
    lateinit var museumApi: MuseumApi

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
        Log.d("ApiProvider", "SharedPreferences initialized.")
        val retrofit = getRetrofit()
        userApi = retrofit.create(UserApi::class.java)
        cardApi = retrofit.create(CardApi::class.java)
        pvPApi = retrofit.create(PvPApi::class.java)
        museumApi = retrofit.create(MuseumApi::class.java)
    }


    fun saveToken(token: String) {
        sharedPreferences.edit { putString("token", token) }
        Log.d("ApiProvider", "Token saved: $token")
    }

    private fun getLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


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
            Log.d("ApiProvider", "Retrieved token for interceptor: $token")
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer $token"
                )
                .build()
            chain.proceed(request)
        }
    }
}
