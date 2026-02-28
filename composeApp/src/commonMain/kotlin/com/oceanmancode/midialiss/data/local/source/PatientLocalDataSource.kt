package com.oceanmancode.midialiss.data.local.source

import com.oceanmancode.midialiss.data.local.dao.PatientDao
import com.oceanmancode.midialiss.data.local.entity.PatientEntity

class PatientLocalDataSource(
    private val patientDao: PatientDao
) {
    suspend fun getById(patientId: String): PatientEntity? {
        return patientDao.findById(patientId)
    }

    suspend fun upsert(entity: PatientEntity) {
        patientDao.upsert(entity)
    }
}