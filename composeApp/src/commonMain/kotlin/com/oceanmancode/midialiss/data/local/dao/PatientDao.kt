package com.oceanmancode.midialiss.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oceanmancode.midialiss.data.local.entity.PatientEntity

@Dao
interface PatientDao {

    @Query("SELECT * FROM patients WHERE id = :patientId LIMIT 1")
    suspend fun findById(patientId: String): PatientEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(patient: PatientEntity)
}