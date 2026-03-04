package com.oceanmancode.midialiss.data.repository.mapper


import com.oceanmancode.midialiss.data.local.entity.PatientEntity
import com.oceanmancode.midialiss.data.local.source.patient.PatientListItem
import com.oceanmancode.midialiss.domain.model.Patient


fun PatientEntity.toDomain(): Patient =
    Patient(
        id = id,
        firstName = firstName,
        lastName = lastName,
        secondLastName = secondLastName,
        birthDate = birthDate,
        curp = curp,
        dryWeightKg = dryWeightKg,
        lastSessionAtEpochMillis = null
    )

fun Patient.toEntity(): PatientEntity =
    PatientEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        secondLastName = secondLastName,
        birthDate = birthDate,
        curp = curp,
        dryWeightKg = dryWeightKg
    )

fun PatientListItem.toDomain(): Patient =
    Patient(
        id = id,
        firstName = firstName,
        lastName = lastName,
        secondLastName = secondLastName,
        birthDate = birthDate,
        curp = curp,
        dryWeightKg = dryWeightKg,
        lastSessionAtEpochMillis = lastSessionAtEpochMillis
    )