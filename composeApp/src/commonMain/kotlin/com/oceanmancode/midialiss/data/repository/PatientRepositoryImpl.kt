package com.oceanmancode.midialiss.data.repository

import com.oceanmancode.midialiss.data.local.entity.PatientEntity
import com.oceanmancode.midialiss.data.local.source.patient.PatientLocalDataSource
import com.oceanmancode.midialiss.data.repository.mapper.toDomain
import com.oceanmancode.midialiss.data.repository.mapper.toEntity
import com.oceanmancode.midialiss.domain.model.Patient
import com.oceanmancode.midialiss.domain.repository.PatientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class PatientRepositoryImpl(
    private val local: PatientLocalDataSource
) : PatientRepository {

    override fun observePatients(): Flow<List<Patient>> {
        return local.observePatientsList().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getById(patientId: String): Patient? {
        return local.getById(patientId)?.toDomain()
    }

    override suspend fun upsert(patient: Patient) {
        // Tu local se llama save, no upsert
        local.save(patient.toEntity())
    }

    override suspend fun deleteById(patientId: String) {
        local.deleteById(patientId)
    }
}