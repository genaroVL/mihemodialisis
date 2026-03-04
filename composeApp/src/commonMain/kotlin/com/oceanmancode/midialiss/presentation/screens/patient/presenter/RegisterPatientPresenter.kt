package com.oceanmancode.midialiss.presentation.screens.patient.presenter

import com.oceanmancode.midialiss.core.generateRandomId
import com.oceanmancode.midialiss.domain.model.Patient
import com.oceanmancode.midialiss.domain.usecase.patient.PatientUseCases
import com.oceanmancode.midialiss.presentation.screens.patient.state.RegisterPatientUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Clock

class RegisterPatientPresenter(
    private val patientUseCases: PatientUseCases,
    private val coroutineScope: CoroutineScope
) {
    private val _state = MutableStateFlow(RegisterPatientUiState())
    val state: StateFlow<RegisterPatientUiState> = _state.asStateFlow()

    private var saveJob: Job? = null

    fun onFirstNameChange(v: String) = _state.update { it.copy(firstName = v, firstNameError = null, errorMessage = null) }
    fun onLastNameChange(v: String) = _state.update { it.copy(lastName = v, lastNameError = null, errorMessage = null) }
    fun onSecondLastNameChange(v: String) = _state.update { it.copy(secondLastName = v, errorMessage = null) }
    fun onBirthYearChange(v: String) = _state.update { it.copy(birthDate = v, birthDateError = null, errorMessage = null) }
    fun onCurpChange(v: String) = _state.update { it.copy(curp = v, errorMessage = null) }
    fun onDryWeightChange(v: String) = _state.update { it.copy(dryWeightKg = v, dryWeightError = null, errorMessage = null) }

    fun submit() {
        if (saveJob != null) return

        val s = state.value
        val firstName = s.firstName.trim()
        val lastName = s.lastName.trim()
        val secondLast = s.secondLastName.trim()

        val birthDate = s.birthDate.trim()
        val dryWeightDouble = s.dryWeightKg.trim().replace(",", ".").toDoubleOrNull()


        val firstNameError = if (firstName.isBlank()) "Nombre requerido" else null
        val lastNameError = if (lastName.isBlank()) "Apellido paterno requerido" else null
        val birthDateError = when {
            birthDate.isBlank() -> "Año de nacimiento requerido"
            else -> null
        }
        val dryWeightError = when {
            s.dryWeightKg.trim().isBlank() -> "Peso seco requerido"
            dryWeightDouble == null -> "Peso seco inválido"
            dryWeightDouble <= 0.0 -> "Debe ser mayor a 0"
            dryWeightDouble > 300.0 -> "Peso fuera de rango"
            else -> null
        }

        if (firstNameError != null || lastNameError != null || birthDateError != null || dryWeightError != null) {
            _state.update {
                it.copy(
                    firstNameError = firstNameError,
                    lastNameError = lastNameError,
                    birthDateError  = birthDateError,
                    dryWeightError = dryWeightError
                )
            }
            return
        }

        val curpValue = s.curp.trim().ifBlank { null }?.uppercase()

        saveJob = coroutineScope.launch {
            _state.update { it.copy(isSaving = true, errorMessage = null, savedSuccessfully = false) }

            runCatching {
                val patient = Patient(
                    id = generateRandomId(),
                    firstName = firstName,
                    lastName = lastName,
                    secondLastName = secondLast,
                    birthDate = birthDate,
                    curp = curpValue,
                    dryWeightKg = dryWeightDouble!!,
                    lastSessionAtEpochMillis = null
                )
                patientUseCases.upsertPatient(patient)
            }.onSuccess {
                _state.update { it.copy(isSaving = false, savedSuccessfully = true) }
            }.onFailure { e ->
                _state.update { it.copy(isSaving = false, errorMessage = e.message ?: "No se pudo guardar.") }
            }

            saveJob = null
        }
    }

    fun consumeSaved() {
        _state.update { it.copy(savedSuccessfully = false) }
    }

    fun resetForm() {
        _state.value = RegisterPatientUiState()
    }


}