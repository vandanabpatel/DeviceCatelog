package com.example.devicecatalog.presentation.ui

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import jakarta.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    fun getAppContext(): Context {
        return applicationContext
    }
}
