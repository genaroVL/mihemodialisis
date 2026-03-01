package com.oceanmancode.midialiss.domain.usecase.patient

data class PatientUseCases(
    val observePatients: ObservePatientsUseCase,
    val getPatientById: GetPatientByIdUseCase,
    val upsertPatient: UpsertPatientUseCase,
    val deletePatient: DeletePatientUseCase
)