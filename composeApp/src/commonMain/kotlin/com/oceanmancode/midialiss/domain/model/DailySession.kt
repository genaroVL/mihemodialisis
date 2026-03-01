package com.oceanmancode.midialiss.domain.model

data class DialysisSession(
    val id: String,
    val patientId: String,
    val createdAtEpochMillis: Long,
    val preDialysisWeightKg: Double,
    val dryWeightKgSnapshot: Double,
    val primingMl: Int,
    val ultrafiltrationMl: Int
)