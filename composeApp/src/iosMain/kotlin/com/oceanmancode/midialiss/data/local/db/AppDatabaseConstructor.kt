package com.oceanmancode.midialiss.data.local.db

import androidx.room.Room
import androidx.room.RoomDatabaseConstructor

actual object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    actual override fun initialize(): AppDatabase {

        return Room.databaseBuilder<AppDatabase>(name = iosAppDatabaseFilePath()).build()
    }
}