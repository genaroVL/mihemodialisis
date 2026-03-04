package com.oceanmancode.midialiss.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.oceanmancode.midialiss.core.maskDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
    value: String,
    onChange: (String) -> Unit,
    error: String?,
    label: String = "Fecha de nacimiento (dd/MM/yyyy)",
    modifier: Modifier = Modifier,
) {
    var open by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { raw ->
            onChange(maskDateFormat(raw))
        },
        label = { Text(label) },
        isError = error != null,
        supportingText = { error?.let { Text(it) } },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        trailingIcon = {
            IconButton(onClick = { open = true }) {

                Text("📅")
            }
        },
        modifier = modifier.fillMaxWidth()
    )

    if (open) {
        BirthDatePickerDialog(
            initial = value,
            onDismiss = { open = false },
            onConfirm = { selected ->
                onChange(selected)
                open = false
            }
        )
    }
}