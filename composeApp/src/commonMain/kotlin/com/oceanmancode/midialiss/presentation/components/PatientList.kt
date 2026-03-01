package com.oceanmancode.midialiss.presentation.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oceanmancode.midialiss.domain.model.Patient

@Composable
fun PatientsList(
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
                    Text("Peso seco: ${patient.dryWeightKg} kg", style = MaterialTheme.typography.bodyMedium)
                    patient.curp?.takeIf { it.isNotBlank() }?.let { curp ->
                        Spacer(Modifier.height(2.dp))
                        Text("CURP: $curp", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}