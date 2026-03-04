package com.oceanmancode.midialiss.data.local.source.patient

data class PatientListItem(
    val id: String,
    val firstName: String,
    val lastName: String,
    val secondLastName: String,
    val birthDate: String,
    val curp: String?,
    val dryWeightKg: Double,
    val lastSessionAtEpochMillis: Long?
) {
    val fullName: String
        get() = buildString {
            append(firstName.trim())
            append(" ")
            append(lastName.trim())
            if (secondLastName.isNotBlank()) {
                append(" ")
                append(secondLastName.trim())
            }
        }
}