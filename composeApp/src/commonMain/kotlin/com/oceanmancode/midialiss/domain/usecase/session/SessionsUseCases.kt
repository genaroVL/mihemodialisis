package com.oceanmancode.midialiss.domain.usecase.session

data class SessionsUseCases(
    val calculateUltrafiltration: CalculateUltraFiltrationUseCase,
    val observeSessionsByPatient: ObserveSessionsByPatientUseCase,
    val addDialysisSession: AddDialysisSessionUseCase,
    val deleteDialysisSession: DeleteDialysisSessionUseCase
)