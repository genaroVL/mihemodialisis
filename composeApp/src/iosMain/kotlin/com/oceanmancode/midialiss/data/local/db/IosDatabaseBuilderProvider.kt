package com.oceanmancode.midialiss.data.local.db

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

class IosDatabaseBuilderProvider : DatabaseBuilderProvider {
    override fun provide(): RoomDatabase.Builder<AppDatabase> {
        return Room.databaseBuilder<AppDatabase>(name = iosAppDatabaseFilePath())
    }
}