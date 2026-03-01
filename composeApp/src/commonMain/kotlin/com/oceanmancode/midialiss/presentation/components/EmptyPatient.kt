package com.oceanmancode.midialiss.presentation.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EmptyPatientsState(
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
        Text("Aún no hay pacientes", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(6.dp))
        Text("Registra tu primer paciente para comenzar.", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(18.dp))

        Button(onClick = onRegisterClick) {
            Text("Registrar paciente")
        }
    }
}