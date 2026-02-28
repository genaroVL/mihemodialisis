package com.oceanmancode.midialiss.di

import com.oceanmancode.midialiss.core.createAppCoroutineScope
import com.oceanmancode.midialiss.domain.repository.PatientRepository
import com.oceanmancode.midialiss.domain.usecase.patient.GetPatientById
import com.oceanmancode.midialiss.domain.usecase.patient.PatientUseCases
import com.oceanmancode.midialiss.domain.usecase.patient.SavePatient
import com.oceanmancode.midialiss.presentation.patient.PatientPresenter


import kotlinx.coroutines.CoroutineScope


class PatientModule(
    patientRepository: PatientRepository
) {
    private val useCases = PatientUseCases(
        getPatientById = GetPatientById(patientRepository),
        savePatient = SavePatient(patientRepository)
    )

    fun createPresenter(coroutineScope: CoroutineScope): PatientPresenter {
        return PatientPresenter(
            useCases = useCases,
            coroutineScope = coroutineScope
        )
    }
}