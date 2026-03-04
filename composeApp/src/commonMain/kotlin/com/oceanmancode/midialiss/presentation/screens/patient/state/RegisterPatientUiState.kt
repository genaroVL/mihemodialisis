package com.oceanmancode.midialiss.presentation.screens.patient.state

data class RegisterPatientUiState(
    val isSaving: Boolean = false,
    val errorMessage: String? = null,
    val savedSuccessfully: Boolean = false,

    val firstName: String = "",
    val lastName: String = "",
    val secondLastName: String = "",
    val birthDate: String = "",
    val curp: String = "",
    val dryWeightKg: String = "",

    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val birthDateError: String? = null,
    val dryWeightError: String? = null,
)