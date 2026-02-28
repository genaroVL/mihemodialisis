package com.oceanmancode.midialiss.data.local.db

import androidx.room.RoomDatabase

interface DatabaseBuilderProvider {
    fun provide(): RoomDatabase.Builder<AppDatabase>
}