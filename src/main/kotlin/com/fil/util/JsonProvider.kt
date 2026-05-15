package com.fil.util

import kotlinx.serialization.json.Json

object JsonProvider {

    val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }
}