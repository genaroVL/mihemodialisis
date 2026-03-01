package com.oceanmancode.midialiss.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.oceanmancode.midialiss.data.local.db.DatabaseBuilderProvider
import com.oceanmancode.midialiss.di.AppContainer
import com.oceanmancode.midialiss.presentation.patient.PatientsScreen


@Composable
fun AppRoot(databaseBuilderProvider: DatabaseBuilderProvider) {
    val appContainer = remember(databaseBuilderProvider) {
        AppContainer(databaseBuilderProvider)
    }

    val coroutineScope = rememberCoroutineScope()

    val patientsPresenter = remember(appContainer) {
        appContainer.createPatientsPresenter(coroutineScope)
    }

    PatientsScreen(
        presenter = patientsPresenter,
        onRegisterClick = {
            // luego aquí navegas a Register screen
            // por ahora puede quedar vacío o log
        },
        onCalculateClick = {
            // luego aquí navegas a Calculate screen (free mode)
        },
        onPatientClick = { patientId ->
            // luego navegas a PatientDetail(patientId)
        }
    )
}

