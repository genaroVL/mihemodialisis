package com.oceanmancode.midialiss.presentation.patient

import com.oceanmancode.midialiss.domain.model.Patient
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun PatientScreen(
    presenter: PatientPresenter
) {
    val state by presenter.state.collectAsState()

    var firstName by remember { mutableStateOf("") }
    var lastNameFather by remember { mutableStateOf("") }
    var lastNameMother by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Patient", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lastNameFather,
            onValueChange = { lastNameFather = it },
            label = { Text("Last name (father)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lastNameMother,
            onValueChange = { lastNameMother = it },
            label = { Text("Last name (mother)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val patient = Patient(
                    id = "",
                    firstName = firstName.trim(),
                    lastName = lastNameFather.trim(),
                    secondLastName = lastNameMother.trim(),
                    phoneNumber = phoneNumber.trim()
                )
                presenter.savePatient(patient)
            },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (state.isLoading) "Saving..." else "Save")
        }

        state.errorMessage?.let { message ->
            Text(message, color = MaterialTheme.colorScheme.error)
        }

        state.patient?.let { saved ->
            Text("Saved: ${saved.firstName} ${saved.secondLastName}")
        }
    }
}