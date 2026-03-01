package com.oceanmancode.midialiss.data.repository.mapper


import com.oceanmancode.midialiss.data.local.entity.DialysisSessionEntity
import com.oceanmancode.midialiss.domain.model.DialysisSession

fun DialysisSessionEntity.toDomain(): DialysisSession =
    DialysisSession(
        id = id,
        patientId = patientId,
        createdAtEpochMillis = createdAtEpochMillis,
        preDialysisWeightKg = preDialysisWeightKg,
        dryWeightKgSnapshot = dryWeightKgSnapshot,
        primingMl = primingMl,
        ultrafiltrationMl = ultrafiltrationMl
    )

fun DialysisSession.toEntity(): DialysisSessionEntity =
    DialysisSessionEntity(
        id = id,
        patientId = patientId,
        createdAtEpochMillis = createdAtEpochMillis,
        preDialysisWeightKg = preDialysisWeightKg,
        dryWeightKgSnapshot = dryWeightKgSnapshot,
        primingMl = primingMl,
        ultrafiltrationMl = ultrafiltrationMl
    )