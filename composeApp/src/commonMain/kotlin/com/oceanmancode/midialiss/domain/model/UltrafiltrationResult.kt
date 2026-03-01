package com.oceanmancode.midialiss.domain.model

data class UltrafiltrationResult(
    val ultrafiltrationKg: Double,
    val ultrafiltrationMl: Int,
    val warning: String? = null
)