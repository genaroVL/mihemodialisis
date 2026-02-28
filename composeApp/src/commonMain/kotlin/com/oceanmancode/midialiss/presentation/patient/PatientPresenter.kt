package com.oceanmancode.midialiss.presentation.patient

import com.oceanmancode.midialiss.domain.model.Patient
import com.oceanmancode.midialiss.domain.usecase.patient.PatientUseCases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PatientPresenter(
    private val useCases: PatientUseCases,
    private val coroutineScope: CoroutineScope
) {
    private val mutableState = MutableStateFlow(PatientUiState())
    val state: StateFlow<PatientUiState> = mutableState.asStateFlow()

    fun loadPatient(patientId: String) {
        mutableState.value = mutableState.value.copy(isLoading = true, errorMessage = null)

        coroutineScope.launch {
            runCatching { useCases.getPatientById(patientId) }
                .onSuccess { patient ->
                    mutableState.value = mutableState.value.copy(isLoading = false, patient = patient)
                }
                .onFailure { throwable ->
                    mutableState.value = mutableState.value.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Unable to load patient"
                    )
                }
        }
    }

    fun savePatient(patient: Patient) {
        mutableState.value = mutableState.value.copy(isLoading = true, errorMessage = null)

        val patientToSave = patient.ensureId()

        coroutineScope.launch {
            runCatching { useCases.savePatient(patientToSave) }
                .onSuccess {
                    mutableState.value = mutableState.value.copy(
                        isLoading = false,
                        patient = patientToSave
                    )
                }
                .onFailure { throwable ->
                    mutableState.value = mutableState.value.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Unable to save patient"
                    )
                }
        }
    }

    fun clearError() {
        mutableState.value = mutableState.value.copy(errorMessage = null)
    }

    fun close() {
        coroutineScope.cancel()
    }
}

private fun Patient.ensureId(): Patient {
    if (id.isNotBlank()) return this
    val generatedId = "patient_${kotlin.random.Random.nextLong().toString().removePrefix("-")}"
    return copy(id = generatedId)
}