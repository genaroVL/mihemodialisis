package com.oceanmancode.midialiss.data.repository

import com.oceanmancode.midialiss.data.local.source.dailysessions.DialysisSessionLocalDataSource
import com.oceanmancode.midialiss.data.repository.mapper.toDomain
import com.oceanmancode.midialiss.data.repository.mapper.toEntity
import com.oceanmancode.midialiss.domain.model.DialysisSession
import com.oceanmancode.midialiss.domain.repository.DialysisSessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DialysisSessionRepositoryImpl(
    private val local: DialysisSessionLocalDataSource
) : DialysisSessionRepository {

    override fun observeByPatient(patientId: String): Flow<List<DialysisSession>> {
        return local.observeByPatient(patientId).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun add(session: DialysisSession) {
        local.add(session.toEntity())
    }

    override suspend fun deleteById(sessionId: String) {
        local.deleteById(sessionId)
    }
}