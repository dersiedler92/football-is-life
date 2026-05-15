import data.Player

object PlayerFactorCalculator {

    fun oneVsOneAttackFactor(player: Player): Double {

        val stats = player.playerStats

        return (
                0.35 * stats.dribbling +
                        0.20 * stats.technique +
                        0.15 * stats.acceleration +
                        0.15 * stats.composure +
                        0.15 * stats.strength
                )
    }

    fun oneVsOneDefenseFactor(player: Player): Double {

        val stats = player.playerStats

        return (
                0.35 * stats.tackling +
                        0.25 * stats.marking +
                        0.15 * stats.anticipation +
                        0.15 * stats.reaction +
                        0.10 * stats.strength
                )
    }

    fun passingFactor(player: Player): Double {

        val stats = player.playerStats

        return (
                0.40 * stats.passing +
                        0.25 * stats.vision +
                        0.20 * stats.technique +
                        0.15 * stats.composure
                )
    }

    fun interceptionFactor(player: Player): Double {

        val stats = player.playerStats

        return (
                0.35 * stats.anticipation +
                        0.25 * stats.marking +
                        0.20 * stats.reaction +
                        0.20 * stats.workRate
                )
    }

    fun shootingFactor(player: Player): Double {

        val stats = player.playerStats

        return (
                0.40 * stats.finishing +
                        0.20 * stats.technique +
                        0.20 * stats.composure +
                        0.20 * stats.reaction
                )
    }

    fun blockFactor(player: Player): Double {

        val stats = player.playerStats

        return (
                0.35 * stats.blocking +
                        0.25 * stats.anticipation +
                        0.20 * stats.reaction +
                        0.20 * stats.marking
                )
    }

    fun aerialAttackFactor(player: Player): Double {

        val stats = player.playerStats

        return (
                0.35 * stats.jumping +
                        0.25 * stats.strength +
                        0.20 * stats.anticipation +
                        0.20 * stats.composure
                )
    }

    fun aerialDefenseFactor(player: Player): Double {

        val stats = player.playerStats

        return (
                0.30 * stats.jumping +
                        0.25 * stats.strength +
                        0.25 * stats.marking +
                        0.20 * stats.anticipation
                )
    }

    fun sprintFactor(player: Player): Double {

        val stats = player.playerStats

        return (
                0.60 * stats.acceleration +
                        0.40 * stats.speed
                )
    }

    fun physicalFactor(player: Player): Double {

        val stats = player.playerStats

        return (
                0.40 * stats.strength +
                        0.25 * stats.aggression +
                        0.20 * stats.workRate +
                        0.15 * stats.stamina
                )
    }

    fun goalkeeperOneVsOneFactor(player: Player): Double {

        val stats = player.playerStats

        return (
                0.35 * stats.reaction +
                        0.25 * stats.composure +
                        0.20 * stats.anticipation +
                        0.20 * stats.strength
                )
    }
}