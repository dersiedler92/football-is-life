package com.fil.match

import com.fil.data.Team

object TeamFactorCalculator {

    fun teamStamina(team: Team): Double =
        team.squad
            .map { it.playerStats.stamina }
            .average()

    fun teamDiscipline(team: Team): Double =
        team.squad
            .map { it.playerStats.workRate }
            .average()

    fun teamVision(team: Team): Double =
        team.squad
            .map { it.playerStats.vision }
            .average()

    fun pressingFactor(team: Team): Double {

        val stats = team.teamStats

        return (
                0.45 * stats.tacticalUnderstanding +
                        0.25 * stats.grit +
                        0.15 * stats.confidence +
                        0.15 * teamStamina(team)
                )
    }

    fun defendingFactor(team: Team): Double {

        val stats = team.teamStats

        return (
                0.40 * stats.grit +
                        0.30 * stats.mentalStability +
                        0.15 * stats.harmony +
                        0.15 * teamDiscipline(team)
                )
    }

    fun mentalityFactor(team: Team): Double {

        val stats = team.teamStats

        return (
                0.40 * stats.mentalStability +
                        0.25 * stats.confidence +
                        0.20 * stats.harmony +
                        0.15 * stats.form
                )
    }

    fun tacticalFactor(team: Team): Double {

        val stats = team.teamStats

        return (
                0.50 * stats.tacticalUnderstanding +
                        0.25 * teamVision(team) +
                        0.15 * stats.identityAlignment +
                        0.10 * stats.form
                )
    }

    fun staminaFactor(team: Team): Double {

        val stats = team.teamStats

        return (
                0.70 * teamStamina(team) +
                        0.30 * stats.grit
                )
    }

    fun fanFactor(team: Team): Double {

        val stats = team.teamStats

        return (
                0.55 * stats.fanSupport +
                        0.20 * stats.confidence +
                        0.15 * stats.harmony +
                        0.10 * stats.identityAlignment
                )
    }
}