package com.oceanmancode.midialiss.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

fun createAppCoroutineScope(): CoroutineScope {
    return CoroutineScope(SupervisorJob() + Dispatchers.Main)
}