package com.oceanmancode.midialiss.di

import com.oceanmancode.midialiss.data.local.db.AppDatabase
import com.oceanmancode.midialiss.data.local.db.DatabaseBuilderProvider
import com.oceanmancode.midialiss.data.local.db.DatabaseFactory
import com.oceanmancode.midialiss.data.local.source.dailysessions.DialysisSessionLocalDataSource
import com.oceanmancode.midialiss.data.local.source.patient.PatientLocalDataSource
import com.oceanmancode.midialiss.data.repository.DialysisSessionRepositoryImpl
import com.oceanmancode.midialiss.data.repository.PatientRepositoryImpl
import com.oceanmancode.midialiss.domain.repository.DialysisSessionRepository
import com.oceanmancode.midialiss.domain.repository.PatientRepository

class DataModule(
    databaseBuilderProvider: DatabaseBuilderProvider
) {
    val database: AppDatabase = DatabaseFactory.create(databaseBuilderProvider)

    private val patientLocalDataSource =
        PatientLocalDataSource(database.patientDao())

    private val dialysisSessionLocalDataSource =
        DialysisSessionLocalDataSource(database.dialysisSessionDao())

    val patientRepository: PatientRepository =
        PatientRepositoryImpl(patientLocalDataSource)

    val dialysisSessionRepository: DialysisSessionRepository =
        DialysisSessionRepositoryImpl(dialysisSessionLocalDataSource)
}