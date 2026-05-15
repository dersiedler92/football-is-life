package com.fil.data

import kotlinx.serialization.Serializable

@Serializable
data class TeamStats(
    val harmony: Int,
    val mentalStability: Int,
    val tacticalUnderstanding: Int,
    val fanSupport: Int,
    val confidence: Int,
    val grit: Int,
    val form: Int,
    val identityAlignment: Int
) {

    init {
        val values = listOf(
            harmony,
            mentalStability,
            tacticalUnderstanding,
            fanSupport,
            confidence,
            grit,
            form,
            identityAlignment
        )

        require(values.all { it in 1..100 }) {
            "All team stats must be between 1 and 100."
        }
    }
}