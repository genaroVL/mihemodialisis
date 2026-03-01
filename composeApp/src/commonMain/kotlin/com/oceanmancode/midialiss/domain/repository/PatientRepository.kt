package com.oceanmancode.midialiss.domain.repository

import com.oceanmancode.midialiss.domain.model.Patient
import kotlinx.coroutines.flow.Flow

interface PatientRepository {
    fun observePatients(): Flow<List<Patient>>
    suspend fun getById(patientId: String): Patient?
    suspend fun upsert(patient: Patient)
    suspend fun deleteById(patientId: String)
}
