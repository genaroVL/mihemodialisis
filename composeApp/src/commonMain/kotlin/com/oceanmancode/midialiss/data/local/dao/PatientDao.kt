package com.oceanmancode.midialiss.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.oceanmancode.midialiss.data.local.entity.PatientEntity
import com.oceanmancode.midialiss.data.local.source.patient.PatientListItem
import kotlinx.coroutines.flow.Flow


@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertPatient(patient: PatientEntity)

    @Update
    suspend fun updatePatient(patient: PatientEntity)

    @Query("DELETE FROM patients WHERE id = :patientId")
    suspend fun deletePatientById(patientId: String)

    @Query("SELECT * FROM patients WHERE id = :patientId LIMIT 1")
    suspend fun getPatientById(patientId: String): PatientEntity?

    @Query("""
    SELECT 
        p.id AS id,
        p.firstName AS firstName,
        p.lastName AS lastName,
        p.secondLastName AS secondLastName,
        p.birthYear AS birthYear,
        p.curp AS curp,
        p.dryWeightKg AS dryWeightKg,
        (
            SELECT MAX(s.createdAtEpochMillis)
            FROM dialysis_sessions s
            WHERE s.patientId = p.id
        ) AS lastSessionAtEpochMillis
    FROM patients p
    ORDER BY p.firstName COLLATE NOCASE ASC
""")
    fun observePatientsList(): Flow<List<PatientListItem>>
}