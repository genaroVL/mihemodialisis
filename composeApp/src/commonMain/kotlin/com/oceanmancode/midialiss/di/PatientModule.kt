package com.oceanmancode.midialiss.di

import com.oceanmancode.midialiss.domain.repository.DialysisSessionRepository
import com.oceanmancode.midialiss.domain.repository.PatientRepository
import com.oceanmancode.midialiss.domain.usecase.patient.DeletePatientUseCase
import com.oceanmancode.midialiss.domain.usecase.patient.GetPatientByIdUseCase
import com.oceanmancode.midialiss.domain.usecase.patient.ObservePatientsUseCase
import com.oceanmancode.midialiss.domain.usecase.patient.PatientUseCases
import com.oceanmancode.midialiss.domain.usecase.patient.UpsertPatientUseCase
import com.oceanmancode.midialiss.domain.usecase.session.AddDialysisSessionUseCase
import com.oceanmancode.midialiss.domain.usecase.session.CalculateUltraFiltrationUseCase
import com.oceanmancode.midialiss.domain.usecase.session.DeleteDialysisSessionUseCase
import com.oceanmancode.midialiss.domain.usecase.session.ObserveSessionsByPatientUseCase
import com.oceanmancode.midialiss.domain.usecase.session.SessionsUseCases
import com.oceanmancode.midialiss.presentation.screens.patient.presenter.PatientsPresenter
import com.oceanmancode.midialiss.presentation.screens.patient.presenter.RegisterPatientPresenter
import kotlinx.coroutines.CoroutineScope

class PatientModule(
    patientRepository: PatientRepository,
    dialysisSessionRepository: DialysisSessionRepository
) {
    private val patientUseCases = PatientUseCases(
        observePatients = ObservePatientsUseCase(patientRepository),
        getPatientById = GetPatientByIdUseCase(patientRepository),
        upsertPatient = UpsertPatientUseCase(patientRepository),
        deletePatient = DeletePatientUseCase(patientRepository)
    )

    private val calculateUltrafiltration = CalculateUltraFiltrationUseCase()

    private val sessionUseCases = SessionsUseCases(
        calculateUltrafiltration = calculateUltrafiltration,
        observeSessionsByPatient = ObserveSessionsByPatientUseCase(dialysisSessionRepository),
        addDialysisSession = AddDialysisSessionUseCase(
            repository = dialysisSessionRepository,
            calculateUltrafiltration = calculateUltrafiltration
        ),
        deleteDialysisSession = DeleteDialysisSessionUseCase(dialysisSessionRepository)
    )


    fun createPatientsPresenter(coroutineScope: CoroutineScope): PatientsPresenter {
        return PatientsPresenter(
            patientUseCases = patientUseCases,
            coroutineScope = coroutineScope
        )
    }

    fun createRegisterPatientPresenter(coroutineScope: CoroutineScope): RegisterPatientPresenter {
        return RegisterPatientPresenter(
            patientUseCases = patientUseCases,
            coroutineScope = coroutineScope
        )
    }
}