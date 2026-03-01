package com.oceanmancode.midialiss.domain.usecase.patient

import com.oceanmancode.midialiss.domain.model.Patient
import com.oceanmancode.midialiss.domain.repository.PatientRepository
import kotlinx.coroutines.flow.Flow

class ObservePatientsUseCase(
    private val repository: PatientRepository
) {
    operator fun invoke(): Flow<List<Patient>> = repository.observePatients()
}