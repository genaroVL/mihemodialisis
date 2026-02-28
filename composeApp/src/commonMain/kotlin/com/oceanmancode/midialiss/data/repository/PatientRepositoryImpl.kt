package com.oceanmancode.midialiss.data.repository

import com.oceanmancode.midialiss.data.local.entity.PatientEntity
import com.oceanmancode.midialiss.data.local.source.PatientLocalDataSource
import com.oceanmancode.midialiss.domain.model.Patient
import com.oceanmancode.midialiss.domain.repository.PatientRepository

class PatientRepositoryImpl(
    private val localDataSource: PatientLocalDataSource
) : PatientRepository {

    override suspend fun getById(patientId: String): Patient? {
        return localDataSource.getById(patientId)?.toDomainModel()
    }

    override suspend fun save(patient: Patient) {
        localDataSource.upsert(patient.toEntity())
    }
}

private fun PatientEntity.toDomainModel(): Patient {
    return Patient(
        id = id,
        firstName = firstName,
        lastName = lastName,
        secondLastName = secondLastName,
        phoneNumber = phoneNumber
    )
}

private fun Patient.toEntity(): PatientEntity {
    return PatientEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        secondLastName = secondLastName,
        phoneNumber = phoneNumber
    )
}