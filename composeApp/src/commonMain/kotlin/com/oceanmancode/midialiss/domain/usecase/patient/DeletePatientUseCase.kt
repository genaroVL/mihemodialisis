package com.oceanmancode.midialiss.domain.usecase.patient

import com.oceanmancode.midialiss.domain.repository.PatientRepository

class DeletePatientUseCase(
    private val repository: PatientRepository
) {
    suspend operator fun invoke(patientId: String) {
        repository.deleteById(patientId)
    }
}