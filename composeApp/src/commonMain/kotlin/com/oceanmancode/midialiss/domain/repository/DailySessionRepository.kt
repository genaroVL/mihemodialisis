package com.oceanmancode.midialiss.domain.repository

import com.oceanmancode.midialiss.domain.model.DialysisSession
import kotlinx.coroutines.flow.Flow

interface DialysisSessionRepository {
    fun observeByPatient(patientId: String): Flow<List<DialysisSession>>
    suspend fun add(session: DialysisSession)
    suspend fun deleteById(sessionId: String)
}