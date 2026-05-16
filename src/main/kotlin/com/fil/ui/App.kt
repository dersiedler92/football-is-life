package com.fil.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.fil.audio.BgmPlayer
import com.fil.util.loadImageBitmap

@Composable
fun App(
    onQuitGame: () -> Unit
) {
    LaunchedEffect(Unit) {
        BgmPlayer.playLoop("/bgm/opening.wav")
    }

    val backgroundImage = remember {
        loadImageBitmap("/images/title.png")
    }

    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                bitmap = backgroundImage,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.65f)
            ) {
                MainScreen(
                    onQuitGame = onQuitGame
                )
            }
        }
    }
}