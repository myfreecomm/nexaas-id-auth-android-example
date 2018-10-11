package com.nexaas.android.nexaasidauth

import android.app.Application
import android.content.Context

class NexxasIdAuthApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        private var instance: Application? = null

        val appContext: Context
            get() = instance!!.applicationContext

        val `package`: String
            get() = appContext.packageName
    }
}