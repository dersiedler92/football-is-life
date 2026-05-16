package com.fil

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.fil.ui.App

fun main() = application {
    val windowState = rememberWindowState(
        placement = WindowPlacement.Maximized
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