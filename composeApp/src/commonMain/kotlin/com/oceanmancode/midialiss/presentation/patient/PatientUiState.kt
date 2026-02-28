package com.oceanmancode.midialiss.presentation.patient

import com.oceanmancode.midialiss.domain.model.Patient

data class PatientUiState(
    val isLoading: Boolean = false,
    val patient: Patient? = null,
    val errorMessage: String? = null
)