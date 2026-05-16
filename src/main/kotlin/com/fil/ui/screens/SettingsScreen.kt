package com.fil.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            "Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        Text("Audio, Display und Gameplay Settings kommen hier rein.")
    }
}