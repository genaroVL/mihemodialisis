package com.oceanmancode.midialiss

import androidx.compose.ui.window.ComposeUIViewController
import com.oceanmancode.midialiss.data.local.db.IosDatabaseBuilderProvider
import com.oceanmancode.midialiss.presentation.AppRoot

fun MainViewController() = ComposeUIViewController {
    AppRoot(databaseBuilderProvider = IosDatabaseBuilderProvider())
}