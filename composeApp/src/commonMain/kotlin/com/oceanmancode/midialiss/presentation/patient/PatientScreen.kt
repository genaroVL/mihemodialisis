package com.oceanmancode.midialiss.presentation.patient

import androidx.compose.foundation.clickable
import com.oceanmancode.midialiss.domain.model.Patient
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientsScreen(
    presenter: PatientsPresenter,
    onRegisterClick: () -> Unit,
    onCalculateClick: () -> Unit,
    onPatientClick: (patientId: String) -> Unit
) {
    LaunchedEffect(Unit) { presenter.start() }
    val state by presenter.state.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Patients") }) },
        bottomBar = {
            BottomActionsBar(
                onRegister = onRegisterClick,
                onCalculate = onCalculateClick
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            when {
                state.isLoading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                state.errorMessage != null -> {
                    ErrorState(
                        message = state.errorMessage ?: "Error",
                        onRetry = { presenter.start() } // start() ya protege el doble start
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

@Composable
private fun PatientsList(
    patients: List<Patient>,
    onPatientClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(patients, key = { it.id }) { patient ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onPatientClick(patient.id) }
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text(patient.fullName, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(4.dp))
                    Text("Dry weight: ${patient.dryWeightKg} kg", style = MaterialTheme.typography.bodyMedium)
                    patient.curp?.takeIf { it.isNotBlank() }?.let {
                        Spacer(Modifier.height(2.dp))
                        Text("CURP: $it", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyPatientsState(onRegisterClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // “Imagen” simple y bonita sin recursos
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 2.dp
        ) {
            Box(
                modifier = Modifier.size(120.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("🩺", style = MaterialTheme.typography.displayMedium)
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("No patients yet", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(6.dp))
        Text(
            "Register your first patient to start calculating ultrafiltration.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(18.dp))
        Button(onClick = onRegisterClick) {
            Text("Register patient")
        }
    }
}
@Composable
private fun BottomActionsBar(
    onRegister: () -> Unit,
    onCalculate: () -> Unit
) {
    Surface(tonalElevation = 2.dp) {
        Row(
            Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = onRegister
            ) { Text("Register") }

            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCalculate
            ) { Text("Calculate") }
        }
    }
}

@Composable
private fun ErrorState(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(message, style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(12.dp))
        Button(onClick = onRetry) { Text("Retry") }
    }
}