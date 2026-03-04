package com.oceanmancode.midialiss.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oceanmancode.midialiss.core.maskDateFormat
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

@Composable
fun BirthDatePickerDialog(
    initial: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    val currentYear = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .year
    val maskedInitial = remember(initial) { maskDateFormat(initial) }

    val initParts = remember(maskedInitial, currentYear) {
        parseDatePartsOrDefault(maskedInitial, currentYear)
    }
    val (initD, initM, initY) = initParts

    var year by remember { mutableStateOf(initY) }
    var month by remember { mutableStateOf(initM) }
    var day by remember { mutableStateOf(initD) }

    val maxDay = remember(year, month) { daysInMonth(year, month) }

    LaunchedEffect(maxDay) {
        if (day > maxDay) day = maxDay
    }

    val years = remember(currentYear) { (currentYear downTo 1900).toList() }
    val months = remember { (1..12).toList() }
    val days = remember(maxDay) { (1..maxDay).toList() }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Selecciona fecha") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    DropdownIntField(
                        label = "Día",
                        value = day,
                        options = days,
                        modifier = Modifier.weight(1f),
                        onSelect = { day = it },
                        format = { it.toString().padStart(2, '0') }
                    )

                    DropdownIntField(
                        label = "Mes",
                        value = month,
                        options = months,
                        modifier = Modifier.weight(1f),
                        onSelect = { month = it },
                        format = { it.toString().padStart(2, '0') }
                    )

                    DropdownIntField(
                        label = "Año",
                        value = year,
                        options = years,
                        modifier = Modifier.weight(1.2f),
                        onSelect = { year = it }
                    )
                }

                Text("Seleccionada: ${formatDdMmYyyy(day, month, year)}")
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(formatDdMmYyyy(day, month, year)) }) {
                Text("Listo")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropdownIntField(
    label: String,
    value: Int,
    options: List<Int>,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    format: (Int) -> String = { it.toString() },
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = format(value),
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { opt ->
                DropdownMenuItem(
                    text = { Text(format(opt)) },
                    onClick = {
                        onSelect(opt)
                        expanded = false
                    }
                )
            }
        }
    }
}

private fun formatDdMmYyyy(day: Int, month: Int, year: Int): String =
    "${day.toString().padStart(2, '0')}/${month.toString().padStart(2, '0')}/$year"

private fun daysInMonth(year: Int, month: Int): Int = when (month) {
    1, 3, 5, 7, 8, 10, 12 -> 31
    4, 6, 9, 11 -> 30
    2 -> if (isLeapYear(year)) 29 else 28
    else -> 30
}

private fun isLeapYear(year: Int): Boolean =
    (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

private data class DateParts(val day: Int, val month: Int, val year: Int)

private fun parseDatePartsOrDefault(masked: String, currentYear: Int): DateParts {
    // masked esperado: "dd/MM/yyyy" pero puede venir incompleto
    val digits = masked.filter { it.isDigit() }

    val day = digits.take(2).toIntOrNull()?.coerceIn(1, 31) ?: 1
    val month = digits.drop(2).take(2).toIntOrNull()?.coerceIn(1, 12) ?: 1
    val year = digits.drop(4).take(4).toIntOrNull()?.let { y ->
        y.coerceIn(1900, currentYear)
    } ?: currentYear

    return DateParts(day, month, year)
}