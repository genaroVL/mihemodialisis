package com.oceanmancode.midialiss.domain.usecase.patient

import com.oceanmancode.midialiss.domain.model.Patient
import com.oceanmancode.midialiss.domain.repository.PatientRepository

class UpsertPatientUseCase(
    private val repository: PatientRepository
) {
    suspend operator fun invoke(patient: Patient) {
        repository.upsert(patient)
    }
}