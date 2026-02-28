package com.oceanmancode.midialiss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.oceanmancode.midialiss.data.local.db.AndroidDatabaseBuilderProvider
import com.oceanmancode.midialiss.di.AppContainer
import com.oceanmancode.midialiss.presentation.AppRoot

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            AppRoot(
                databaseBuilderProvider = AndroidDatabaseBuilderProvider(applicationContext)
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}