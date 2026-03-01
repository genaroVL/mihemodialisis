package com.oceanmancode.midialiss.di

import com.oceanmancode.midialiss.data.local.db.DatabaseBuilderProvider
import com.oceanmancode.midialiss.presentation.patient.PatientsPresenter
import kotlinx.coroutines.CoroutineScope

class AppContainer(
    databaseBuilderProvider: DatabaseBuilderProvider
) {
    private val dataModule = DataModule(databaseBuilderProvider)

    private val patientModule = PatientModule(
        patientRepository = dataModule.patientRepository,
        dialysisSessionRepository = dataModule.dialysisSessionRepository
    )

    fun createPatientsPresenter(coroutineScope: CoroutineScope): PatientsPresenter {
        return patientModule.createPatientsPresenter(coroutineScope)
    }
}
