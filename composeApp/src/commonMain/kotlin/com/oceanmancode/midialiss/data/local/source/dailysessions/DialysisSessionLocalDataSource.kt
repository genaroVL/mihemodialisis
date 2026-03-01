package com.oceanmancode.midialiss.data.local.source.dailysessions

import com.oceanmancode.midialiss.data.local.dao.DialysisSessionDao
import com.oceanmancode.midialiss.data.local.entity.DialysisSessionEntity
import kotlinx.coroutines.flow.Flow

class DialysisSessionLocalDataSource(
    private val dao: DialysisSessionDao
) {
    suspend fun add(entity: DialysisSessionEntity) {
        dao.insertSession(entity)
    }

    suspend fun deleteById(sessionId: String) {
        dao.deleteById(sessionId)
    }

    fun observeByPatient(patientId: String): Flow<List<DialysisSessionEntity>> {
        return dao.observeByPatient(patientId)
    }
}