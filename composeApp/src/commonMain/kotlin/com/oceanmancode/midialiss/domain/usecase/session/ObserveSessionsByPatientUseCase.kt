package com.oceanmancode.midialiss.domain.usecase.session

import com.oceanmancode.midialiss.domain.model.DialysisSession
import com.oceanmancode.midialiss.domain.repository.DialysisSessionRepository
import kotlinx.coroutines.flow.Flow

class ObserveSessionsByPatientUseCase(
    private val repository: DialysisSessionRepository
) {
    operator fun invoke(patientId: String): Flow<List<DialysisSession>> {
        return repository.observeByPatient(patientId)
    }
}