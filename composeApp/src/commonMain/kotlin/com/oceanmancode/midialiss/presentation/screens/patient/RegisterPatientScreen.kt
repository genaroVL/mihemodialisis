package com.oceanmancode.midialiss.presentation.screens.patient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oceanmancode.midialiss.presentation.components.DateField
import com.oceanmancode.midialiss.presentation.components.Loader
import com.oceanmancode.midialiss.presentation.screens.patient.presenter.RegisterPatientPresenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPatientScreen(
    presenter: RegisterPatientPresenter,
    onBack: () -> Unit,
) {
    val state by presenter.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Alta de paciente") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Text("←") }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = state.firstName,
                    onValueChange = presenter::onFirstNameChange,
                    label = { Text("Nombre(s)") },
                    isError = state.firstNameError != null,
                    supportingText = { state.firstNameError?.let { Text(it) } },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = state.lastName,
                    onValueChange = presenter::onLastNameChange,
                    label = { Text("Apellido paterno") },
                    isError = state.lastNameError != null,
                    supportingText = { state.lastNameError?.let { Text(it) } },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = state.secondLastName,
                    onValueChange = presenter::onSecondLastNameChange,
                    label = { Text("Apellido materno") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )


                DateField(
                    value = state.birthDate,
                    onChange = presenter::onBirthYearChange,
                    error =  state.birthDateError,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = state.dryWeightKg,
                    onValueChange = presenter::onDryWeightChange,
                    label = { Text("Peso seco (kg)") },
                    isError = state.dryWeightError != null,
                    supportingText = { state.dryWeightError?.let { Text(it) } },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Si quieres CURP visible, descomenta:
                /*
                OutlinedTextField(
                    value = state.curp,
                    onValueChange = presenter::onCurpChange,
                    label = { Text("CURP (opcional)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                */

                state.errorMessage?.let { msg ->
                    Text(
                        text = msg,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Button(
                    onClick = presenter::submit,
                    enabled = !state.isSaving,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar")
                }
            }


            Loader(visible = state.isSaving)
        }
    }
}