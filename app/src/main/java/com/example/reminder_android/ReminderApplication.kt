package com.example.reminder_android

import android.app.Application
import android.util.Log
import com.example.reminder_android.presentations.data.api.ApiProvider

class ReminderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("ReminderApp", "ReminderApplication onCreate called.")
        ApiProvider.initialize(this)
    }
}