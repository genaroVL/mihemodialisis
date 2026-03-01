package com.oceanmancode.midialiss.data.local.source.patient

import com.oceanmancode.midialiss.data.local.dao.PatientDao
import com.oceanmancode.midialiss.data.local.entity.PatientEntity
import kotlinx.coroutines.flow.Flow


class PatientLocalDataSource(
    private val patientDao: PatientDao
) {
    suspend fun getById(patientId: String): PatientEntity? {
        return patientDao.getPatientById(patientId)
    }

    suspend fun save(entity: PatientEntity) {
        val existing = patientDao.getPatientById(entity.id)
        if (existing == null) {
            patientDao.insertPatient(entity)
        } else {
            patientDao.updatePatient(entity)
        }
    }

    suspend fun deleteById(patientId: String) {
        patientDao.deletePatientById(patientId)
    }

    fun observePatientsList(): Flow<List<PatientListItem>> =
        patientDao.observePatientsList()
}