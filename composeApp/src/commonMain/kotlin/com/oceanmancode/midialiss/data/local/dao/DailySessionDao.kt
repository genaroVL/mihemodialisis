package com.oceanmancode.midialiss.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oceanmancode.midialiss.data.local.entity.DialysisSessionEntity
import com.oceanmancode.midialiss.data.local.source.patient.PatientListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DialysisSessionDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSession(entity: DialysisSessionEntity)

    @Query("DELETE FROM dialysis_sessions WHERE id = :sessionId")
    suspend fun deleteById(sessionId: String)

    @Query("""
        SELECT * FROM dialysis_sessions
        WHERE patientId = :patientId
        ORDER BY createdAtEpochMillis DESC
    """)
    fun observeByPatient(patientId: String): Flow<List<DialysisSessionEntity>>
}