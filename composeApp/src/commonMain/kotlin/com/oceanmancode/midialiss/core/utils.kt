package com.oceanmancode.midialiss.core

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun generateRandomId(): String = Uuid.random().toString()


 fun maskDateFormat(input: String): String {
    val digits = input.filter { it.isDigit() }.take(8)
    val sb = StringBuilder()

    for (i in digits.indices) {
        sb.append(digits[i])
        if (i == 1 || i == 3) sb.append('/')
    }
    return sb.toString()
}