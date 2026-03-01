package com.oceanmancode.midialiss

import android.app.Application
import com.oceanmancode.midialiss.data.local.db.AppDatabaseConstructor

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabaseConstructor.init(this)
    }
}