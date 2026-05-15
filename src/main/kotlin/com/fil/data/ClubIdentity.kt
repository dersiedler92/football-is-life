package com.fil.data

import kotlinx.serialization.Serializable

@Serializable
data class ClubIdentity(
    val tradition: Int,
    val fanaticism: Int,
    val glamour: Int,
    val progressiveness: Int,
    val discipline: Int,
    val internationality: Int,
    val youthFocus: Int,
    val commercialism: Int
) {
    init {
        val values = listOf(
            tradition,
            fanaticism,
            glamour,
            progressiveness,
            discipline,
            internationality,
            youthFocus,
            commercialism
        )

        require(values.all { it in 1..10 }) {
            "All club identity values must be between 1 and 10."
        }
    }
}