package com.fil.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fil.ui.screens.AboutScreen
import com.fil.ui.screens.NewGameScreen
import com.fil.ui.screens.SettingsScreen

@Composable
fun MainScreen(
    onQuitGame: () -> Unit
) {
    var selectedMenu by remember {
        mutableStateOf(MainMenu.NEW_GAME)
    }

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
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
                MainMenu.NEW_GAME -> NewGameScreen()
                MainMenu.SETTINGS -> SettingsScreen()
                MainMenu.ABOUT -> AboutScreen()
            }
        }
    }
}