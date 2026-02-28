package com.oceanmancode.midialiss.data.local.db

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO


object DatabaseFactory {
    fun create(builderProvider: DatabaseBuilderProvider): AppDatabase {
        return builderProvider.provide()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}