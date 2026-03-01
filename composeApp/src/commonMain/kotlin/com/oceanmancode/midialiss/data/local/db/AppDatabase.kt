package com.oceanmancode.midialiss.data.local.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.oceanmancode.midialiss.data.local.dao.PatientDao
import com.oceanmancode.midialiss.data.local.entity.PatientEntity

@Database(
    entities = [PatientEntity::class],
    version = DatabaseConfig.SCHEMA_VERSION,
    exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}