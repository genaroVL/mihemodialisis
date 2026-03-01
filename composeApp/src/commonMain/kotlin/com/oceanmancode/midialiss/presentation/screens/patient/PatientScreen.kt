package com.oceanmancode.midialiss.presentation.screens.patient

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.oceanmancode.midialiss.presentation.components.EmptyPatientsState
import com.oceanmancode.midialiss.presentation.components.ErrorState
import com.oceanmancode.midialiss.presentation.components.PatientsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientsScreen(
    presenter: PatientsPresenter,
    onRegisterClick: () -> Unit,
    onPatientClick: (patientId: String) -> Unit
) {
    LaunchedEffect(Unit) { presenter.start() }
    val state by presenter.state.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Pacientes") }) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                state.errorMessage != null -> {
                    ErrorState(
                        message = state.errorMessage ?: "Ocurrió un error",
                        onRetry = { presenter.start() }
                    )
                }

                state.isEmpty -> {
                    EmptyPatientsState(onRegisterClick = onRegisterClick)
                }

                else -> {
                    PatientsList(
                        patients = state.patients,
                        onPatientClick = onPatientClick
                    )
                }
            }
        }
    }
}