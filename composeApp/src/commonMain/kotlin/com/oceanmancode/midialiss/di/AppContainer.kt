package com.oceanmancode.midialiss.di

import com.oceanmancode.midialiss.data.local.db.DatabaseBuilderProvider
import com.oceanmancode.midialiss.presentation.patient.PatientPresenter
import kotlinx.coroutines.CoroutineScope

class AppContainer(
    databaseBuilderProvider: DatabaseBuilderProvider
) {
    private val dataModule = DataModule(databaseBuilderProvider)
    private val patientModule = PatientModule(dataModule.patientRepository)

    fun createPatientPresenter(coroutineScope: CoroutineScope): PatientPresenter {
        return patientModule.createPresenter(coroutineScope)
    }
}