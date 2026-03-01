package com.oceanmancode.midialiss.presentation.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val nav3SavedStateConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(PatientsRoute::class, PatientsRoute.serializer())
            subclass(CalculateRoute::class, CalculateRoute.serializer())
            subclass(RegisterPatientRoute::class, RegisterPatientRoute.serializer())
            subclass(PatientDetailsRoute::class, PatientDetailsRoute.serializer())
        }
    }
}