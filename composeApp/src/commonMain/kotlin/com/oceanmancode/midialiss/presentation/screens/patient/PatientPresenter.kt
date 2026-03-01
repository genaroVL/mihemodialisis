package com.oceanmancode.midialiss.presentation.screens.patient

import com.oceanmancode.midialiss.domain.usecase.patient.PatientUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PatientsPresenter(
    private val patientUseCases: PatientUseCases,
    private val coroutineScope: CoroutineScope
) {
    private val _state = MutableStateFlow(PatientsUiState())
    val state: StateFlow<PatientsUiState> = _state.asStateFlow()

    private var job: Job? = null

    fun start() {
        if (job != null) return

        job = coroutineScope.launch {
            patientUseCases.observePatients()
                .onStart { _state.update { it.copy(isLoading = true, errorMessage = null) } }
                .catch { e ->
                    _state.update {
                        it.copy(isLoading = false, errorMessage = e.message ?: "Failed to load patients.")
                    }
                }
                .collect { list ->
                    _state.update {
                        it.copy(isLoading = false, errorMessage = null, patients = list)
                    }
                }
        }
    }

    fun stop() {
        job?.cancel()
        job = null
    }
}