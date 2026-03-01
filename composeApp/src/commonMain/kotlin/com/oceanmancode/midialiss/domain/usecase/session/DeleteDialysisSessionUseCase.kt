package com.oceanmancode.midialiss.domain.usecase.session

import com.oceanmancode.midialiss.domain.repository.DialysisSessionRepository

class DeleteDialysisSessionUseCase(
    private val repository: DialysisSessionRepository
) {
    suspend operator fun invoke(sessionId: String) {
        repository.deleteById(sessionId)
    }
}