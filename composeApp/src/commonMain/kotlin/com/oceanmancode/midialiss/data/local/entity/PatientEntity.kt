package com.oceanmancode.midialiss.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "patients"
)
data class PatientEntity(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val lastName: String,
    val secondLastName: String,
    val birthDate: String,
    val curp: String?,
    val dryWeightKg: Double
) {
    fun fullName(): String = buildString {
        append(firstName.trim())
        append(" ")
        append(lastName.trim())
        if (secondLastName.isNotBlank()) {
            append(" ")
            append(secondLastName.trim())
        }
    }
}