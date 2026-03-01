package com.oceanmancode.midialiss.domain.usecase.session

import com.oceanmancode.midialiss.domain.model.DialysisSession
import com.oceanmancode.midialiss.domain.model.UltrafiltrationResult
import com.oceanmancode.midialiss.domain.repository.DialysisSessionRepository

class AddDialysisSessionUseCase(
    private val repository: DialysisSessionRepository,
    private val calculateUltrafiltration: CalculateUltraFiltrationUseCase
) {

    data class Input(
        val sessionId: String,
        val patientId: String,
        val createdAtEpochMillis: Long,
        val preDialysisWeightKg: Double,
        val dryWeightKgSnapshot: Double,
        val primingMl: Int = 300
    )

    data class Output(
        val session: DialysisSession,
        val calculation: UltrafiltrationResult
    )

    suspend operator fun invoke(input: Input): Output {
        val calc = calculateUltrafiltration(
            preDialysisWeightKg = input.preDialysisWeightKg,
            dryWeightKg = input.dryWeightKgSnapshot,
            primingMl = input.primingMl
        )

        val session = DialysisSession(
            id = input.sessionId,
            patientId = input.patientId,
            createdAtEpochMillis = input.createdAtEpochMillis,
            preDialysisWeightKg = input.preDialysisWeightKg,
            dryWeightKgSnapshot = input.dryWeightKgSnapshot,
            primingMl = input.primingMl,
            ultrafiltrationMl = calc.ultrafiltrationMl
        )

        repository.add(session)

        return Output(
            session = session,
            calculation = calc
        )
    }
}