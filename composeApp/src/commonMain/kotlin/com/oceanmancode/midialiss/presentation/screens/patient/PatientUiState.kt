package com.oceanmancode.midialiss.presentation.screens.patient

import com.oceanmancode.midialiss.domain.model.Patient

data class PatientsUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val patients: List<Patient> = emptyList()
) {
    val isEmpty: Boolean get() = !isLoading && errorMessage == null && patients.isEmpty()
}