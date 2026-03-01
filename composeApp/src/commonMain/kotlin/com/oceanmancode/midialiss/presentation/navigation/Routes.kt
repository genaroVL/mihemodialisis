package com.oceanmancode.midialiss.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonShapes


@Serializable
sealed interface AppRoute : NavKey


@Serializable
sealed interface TopLevelRoute : AppRoute {
    val title: String
    val iconKey: String
}

@Serializable
data object PatientsRoute : TopLevelRoute {
    override val title = "Pacientes"
    override val iconKey = "user_account"
}

@Serializable
data object CalculateRoute : TopLevelRoute {
    override val title = "Calcular"
    override val iconKey = "calculator"
}
@Serializable
data object RegisterPatientRoute : AppRoute

@Serializable
data class PatientDetailsRoute(val patientId: String) : AppRoute