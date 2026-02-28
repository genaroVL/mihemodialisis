package com.oceanmancode.midialiss.data.local.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class AndroidDatabaseBuilderProvider(
    private val applicationContext: Context
) : DatabaseBuilderProvider {

    override fun provide(): RoomDatabase.Builder<AppDatabase> {
        val databaseFile = applicationContext.getDatabasePath(DatabaseConfig.DATABASE_NAME)

        return Room.databaseBuilder<AppDatabase>(
            context = applicationContext,
            name = databaseFile.absolutePath
        )
    }
}