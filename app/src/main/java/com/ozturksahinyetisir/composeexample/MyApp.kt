package com.ozturksahinyetisir.composeexample

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: MyApp? = null

        val context: Context
            get() = instance!!.applicationContext
    }
}