package com.oceanmancode.midialiss.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.oceanmancode.midialiss.data.local.db.DatabaseBuilderProvider
import com.oceanmancode.midialiss.di.AppContainer
import com.oceanmancode.midialiss.presentation.patient.PatientScreen


@Composable
fun AppRoot(databaseBuilderProvider: DatabaseBuilderProvider) {
    val appContainer = remember(databaseBuilderProvider) {
        AppContainer(databaseBuilderProvider)
    }

    val coroutineScope = rememberCoroutineScope()

    val patientPresenter = remember(appContainer) {
        appContainer.createPatientPresenter(coroutineScope)
    }

    PatientScreen(presenter = patientPresenter)
}

