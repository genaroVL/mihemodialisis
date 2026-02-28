package com.oceanmancode.midialiss.di

import com.oceanmancode.midialiss.data.local.db.AppDatabase
import com.oceanmancode.midialiss.data.local.db.DatabaseBuilderProvider
import com.oceanmancode.midialiss.data.local.db.DatabaseFactory
import com.oceanmancode.midialiss.data.local.source.PatientLocalDataSource
import com.oceanmancode.midialiss.data.repository.PatientRepositoryImpl
import com.oceanmancode.midialiss.domain.repository.PatientRepository

class DataModule(
    databaseBuilderProvider: DatabaseBuilderProvider
) {
    val database: AppDatabase = DatabaseFactory.create(databaseBuilderProvider)

    private val patientLocalDataSource =
        PatientLocalDataSource(database.patientDao())

    val patientRepository: PatientRepository =
        PatientRepositoryImpl(patientLocalDataSource)
}