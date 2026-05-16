package com.fil.util

import androidx.compose.ui.graphics.toComposeImageBitmap
import java.io.BufferedInputStream
import javax.imageio.ImageIO

fun loadImageBitmap(resourcePath: String) =
    object {}::class.java
        .getResourceAsStream(resourcePath)
        ?.let { BufferedInputStream(it) }
        ?.use { ImageIO.read(it) }
        ?.toComposeImageBitmap()
        ?: error("Could not load image: $resourcePath")