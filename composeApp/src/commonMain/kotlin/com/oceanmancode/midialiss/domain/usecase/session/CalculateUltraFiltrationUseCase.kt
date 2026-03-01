package com.oceanmancode.midialiss.domain.usecase.session

import com.oceanmancode.midialiss.domain.model.UltrafiltrationResult
import kotlin.math.roundToInt

class CalculateUltraFiltrationUseCase {
    operator fun invoke(
        preDialysisWeightKg: Double,
        dryWeightKg: Double,
        primingMl: Int = 300
    ): UltrafiltrationResult {

        val safePre = preDialysisWeightKg.takeIf { it.isFinite() && it > 0.0 } ?: 0.0
        val safeDry = dryWeightKg.takeIf { it.isFinite() && it > 0.0 } ?: 0.0
        val safePrimingMl = primingMl.coerceAtLeast(0)

        // 300 ml ≈ 0.3 kg (agua). Convertimos ml a kg:
        val primingKg = safePrimingMl / 1000.0

        val ufKg = (safePre + primingKg) - safeDry

        val warning = when {
            safeDry <= 0.0 -> "Dry weight must be greater than 0."
            safePre <= 0.0 -> "Pre-dialysis weight must be greater than 0."
            ufKg < 0.0 -> "Patient is below dry weight (result is negative)."
            else -> null
        }

        // Si es negativo, lo dejamos negativo en kg, pero ml lo dejamos en 0 para no confundir UI.
        val ufMl = if (ufKg <= 0.0) 0 else (ufKg * 1000.0).roundToInt()

        return UltrafiltrationResult(
            ultrafiltrationKg = ufKg,
            ultrafiltrationMl = ufMl,
            warning = warning
        )
    }
}