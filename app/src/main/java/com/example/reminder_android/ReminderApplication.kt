package com.example.reminder_android

import android.app.Application
import com.example.reminder_android.presentations.data.api.ApiProvider

class ReminderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiProvider.initialize(this)
    }
}