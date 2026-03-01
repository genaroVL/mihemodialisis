package com.oceanmancode.midialiss.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "dialysis_sessions",
    foreignKeys = [
        ForeignKey(
            entity = PatientEntity::class,
            parentColumns = ["id"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.NO_ACTION
        )
    ],
    indices = [
        Index(value = ["patientId"]),
        Index(value = ["createdAtEpochMillis"])
    ]
)
data class DialysisSessionEntity(
    @PrimaryKey
    val id: String,
    val patientId: String,
    val createdAtEpochMillis: Long,
    /**
     * Weight when the patient arrives (kg)
     */
    val preDialysisWeightKg: Double,
    /**
     * Snapshot of the dry weight used in the calculation (kg)
     */
    val dryWeightKgSnapshot: Double,

    /**
     * Priming volume in ml (usually 300)
     */
    val primingMl: Int,
    /**
     * Final result in ml
     */
    val ultrafiltrationMl: Int
)