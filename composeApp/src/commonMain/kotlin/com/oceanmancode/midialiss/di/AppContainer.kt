package com.oceanmancode.midialiss.di

import com.oceanmancode.midialiss.data.local.db.DatabaseBuilderProvider
import com.oceanmancode.midialiss.presentation.screens.patient.presenter.PatientsPresenter
import com.oceanmancode.midialiss.presentation.screens.patient.presenter.RegisterPatientPresenter
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
    fun createRegisterPatientPresenter(coroutineScope: CoroutineScope): RegisterPatientPresenter {
        return patientModule.createRegisterPatientPresenter(coroutineScope)
    }
}
