package com.fil

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val windowState = rememberWindowState(
        placement = WindowPlacement.Fullscreen
    )

    Window(
        onCloseRequest = {
            // Intentionally blocked.
            // Use the in-game Quit Game button instead.
        },
        title = "Football Is Life",
        state = windowState,
        undecorated = true,
        resizable = false
    ) {
        App(
            onQuitGame = ::exitApplication
        )
    }
}

@Composable
@Preview
fun App(
    onQuitGame: () -> Unit
) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen(
                onQuitGame = onQuitGame
            )
        }
    }
}

@Composable
fun MainScreen(
    onQuitGame: () -> Unit
) {
    var selectedMenu by remember { mutableStateOf(MainMenu.DASHBOARD) }

    Row(modifier = Modifier.fillMaxSize()) {
        Sidebar(
            selected = selectedMenu,
            onSelect = { selectedMenu = it },
            onQuitGame = onQuitGame
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            when (selectedMenu) {
                MainMenu.DASHBOARD -> DashboardScreen()
                MainMenu.SQUAD -> SquadScreen()
                MainMenu.MATCH -> MatchScreen()
                MainMenu.CLUB -> ClubScreen()
            }
        }
    }
}

enum class MainMenu(val label: String) {
    DASHBOARD("Dashboard"),
    SQUAD("Kader"),
    MATCH("Spiel"),
    CLUB("Verein")
}

@Composable
fun Sidebar(
    selected: MainMenu,
    onSelect: (MainMenu) -> Unit,
    onQuitGame: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(220.dp),
        tonalElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Football Legacy",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            MainMenu.entries.forEach { item ->
                Button(
                    onClick = { onSelect(item) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = if (item == selected) {
                        ButtonDefaults.buttonColors()
                    } else {
                        ButtonDefaults.outlinedButtonColors()
                    }
                ) {
                    Text(item.label)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onQuitGame,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Quit Game")
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            "Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "Willkommen, Trainer. Deine Reise beginnt in der untersten Liga."
        )
    }
}

@Composable
fun SquadScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            "Kader",
            style = MaterialTheme.typography.headlineMedium
        )

        listOf(
            "Emir Kaya - ZM - 62%",
            "Luis Ferreira - ST - 58%",
            "Jonas Eberhardt - TW - 66%"
        ).forEach { player ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = player,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun MatchScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            "Match Engine",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "Hier kommt später das 3x6-Zonenfeld mit Duellen rein."
        )
    }
}

@Composable
fun ClubScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            "Verein",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "Identität, Fans, Stadion, Jugendcamp und Vereinsgeschichte."
        )
    }
}