package com.fil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            "About Football Is Life",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "Football Is Life ist ein storygetriebener Fußballmanager über Vereinsidentität, Aufstieg, Loyalität und dramatische Fußballmomente."
        )

        Text("Version: 1.0.0")
        Text("Built with Kotlin + Compose Desktop.")
    }
}