package com.fil.data

import kotlinx.serialization.Serializable

@Serializable
data class PlayerStats(

    // Technical
    val technique: Int,
    val dribbling: Int,
    val passing: Int,
    val finishing: Int,
    val firstTouch: Int,
    val crossing: Int,

    // Mental
    val anticipation: Int,
    val composure: Int,
    val vision: Int,
    val aggression: Int,
    val leadership: Int,
    val workRate: Int,

    // Physical
    val speed: Int,
    val acceleration: Int,
    val stamina: Int,
    val strength: Int,
    val jumping: Int,
    val reaction: Int,

    // Defensive
    val tackling: Int,
    val marking: Int,
    val blocking: Int
) {

    init {

        val values = listOf(
            technique,
            dribbling,
            passing,
            finishing,
            firstTouch,
            crossing,

            anticipation,
            composure,
            vision,
            aggression,
            leadership,
            workRate,

            speed,
            acceleration,
            stamina,
            strength,
            jumping,
            reaction,

            tackling,
            marking,
            blocking
        )

        require(values.all { it in 1..100 }) {
            "All player stats must be between 1 and 100."
        }
    }
}