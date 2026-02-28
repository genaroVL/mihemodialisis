package com.oceanmancode.midialiss.domain.usecase.patient

import com.oceanmancode.midialiss.domain.model.Patient
import com.oceanmancode.midialiss.domain.repository.PatientRepository

class GetPatientById(
    private val repository: PatientRepository
) {
    suspend operator fun invoke(patientId: String): Patient? {
        return repository.getById(patientId)
    }
}