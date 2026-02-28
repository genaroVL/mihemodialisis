package com.oceanmancode.midialiss.data.local.db

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

class IosDatabaseBuilderProvider : DatabaseBuilderProvider {

    override fun provide(): RoomDatabase.Builder<AppDatabase> {
        val databasePath = documentDirectoryPath() + "/" + DatabaseConfig.DATABASE_NAME
        return Room.databaseBuilder<AppDatabase>(name = databasePath)
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectoryPath(): String {
        val url = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        return requireNotNull(url?.path)
    }
}