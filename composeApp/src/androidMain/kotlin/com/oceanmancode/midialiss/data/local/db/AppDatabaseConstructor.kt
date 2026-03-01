package com.oceanmancode.midialiss.data.local.db

import android.content.Context
import androidx.room.Room

actual object AppDatabaseConstructor : androidx.room.RoomDatabaseConstructor<AppDatabase> {

    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    actual override fun initialize(): AppDatabase {
        val dbFile = appContext.getDatabasePath(DatabaseConfig.DATABASE_NAME)

        return Room.databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        ).build()
    }
}