package com.oceanmancode.midialiss.domain.repository

import com.oceanmancode.midialiss.domain.model.Patient

interface PatientRepository {
    suspend fun getById(patientId: String): Patient?
    suspend fun save(patient: Patient)
}