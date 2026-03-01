package com.oceanmancode.midialiss.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.oceanmancode.midialiss.presentation.navigation.CalculateRoute
import com.oceanmancode.midialiss.presentation.navigation.PatientsRoute
import com.oceanmancode.midialiss.presentation.navigation.TopLevelRoute
import com.oceanmancode.midialiss.presentation.screens.patient.PatientsPresenter
import com.oceanmancode.midialiss.presentation.screens.patient.PatientsScreen
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.oceanmancode.midialiss.data.local.db.DatabaseBuilderProvider
import com.oceanmancode.midialiss.di.AppContainer
import com.oceanmancode.midialiss.presentation.navigation.AppBottomBar
import com.oceanmancode.midialiss.presentation.navigation.PatientDetailsRoute
import com.oceanmancode.midialiss.presentation.navigation.RegisterPatientRoute
import com.oceanmancode.midialiss.presentation.navigation.nav3SavedStateConfig
import com.oceanmancode.midialiss.presentation.screens.calculate.CalculateScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot(
    databaseBuilderProvider: DatabaseBuilderProvider
) {
    val tabs = remember { listOf<TopLevelRoute>(PatientsRoute, CalculateRoute) }
    val appContainer = remember(databaseBuilderProvider){
        AppContainer(databaseBuilderProvider)
    }
    val coroutineScope= rememberCoroutineScope()
    val patientsPresenter = remember(appContainer){
        appContainer.createPatientsPresenter(coroutineScope)
    }
    val backStack = rememberNavBackStack(
        nav3SavedStateConfig,
        PatientsRoute
    )

    val selectedTab = (backStack.firstOrNull() as? TopLevelRoute) ?: PatientsRoute

    fun switchTab(to: TopLevelRoute) {
        if (to == selectedTab) return
        backStack.clear()
        backStack.add(to)
    }

    Scaffold(
        bottomBar = {
            AppBottomBar(
                tabs = tabs,
                selected = selectedTab,
                onSelect = { switchTab(it) }
            )
        }
    ) { padding ->
        NavDisplay(
            backStack = backStack,
            onBack = { if (backStack.size > 1) backStack.removeLast() },
            entryProvider = entryProvider {

                entry<PatientsRoute> {
                    PatientsScreen(
                        presenter = patientsPresenter,
                        onRegisterClick = { backStack.add(RegisterPatientRoute) },
                        onPatientClick = { patientId ->
                            backStack.add(PatientDetailsRoute(patientId))
                        }
                    )
                }

                entry<CalculateRoute> {
                    CalculateScreen()
                }

                entry<RegisterPatientRoute> {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Registrar paciente") },
                                navigationIcon = {
                                    IconButton(onClick = { backStack.removeLast() }) {
                                        Text("←")
                                    }
                                }
                            )
                        }
                    ) { inner ->
                        Box(
                            modifier = Modifier.padding(inner),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Pantalla en construcción")
                        }
                    }
                }

                entry<PatientDetailsRoute> { key ->
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Detalle del paciente") },
                                navigationIcon = {
                                    IconButton(onClick = { backStack.removeLast() }) {
                                        Text("←")
                                    }
                                }
                            )
                        }
                    ) { inner ->
                        Box(
                            modifier = Modifier.padding(inner),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Paciente: ${key.patientId}")
                        }
                    }
                }
            },
            modifier = Modifier.padding(padding)
        )
    }
}