package com.fil.generation

import com.fil.data.Club
import com.fil.data.Player
import com.fil.data.Team
import com.fil.data.TeamStats
import kotlin.random.Random

object TeamGenerator {

    fun generateTeam(
        teamId: Int,
        club: Club,
        firstPlayerId: Int,
        random: Random
    ): Team {
        val players = generateSquad(
            teamName = club.name,
            firstPlayerId = firstPlayerId,
            random = random
        )

        return Team(
            teamId = teamId,
            name = club.name,
            city = extractCity(club.name),
            country = "Germany",
            squad = players,
            teamStats = generateTeamStats(club)
        )
    }

    private fun generateSquad(
        teamName: String,
        firstPlayerId: Int,
        random: Random
    ): List<Player> {
        return List(22) { index ->
            PlayerGenerator.generate(
                playerId = firstPlayerId + index,
                teamName = teamName,
                nationality = "Germany",
                random = random
            )
        }
    }

    private fun generateTeamStats(
        club: Club
    ): TeamStats {
        val identity = club.identity

        return TeamStats(
            harmony = 50,
            mentalStability = 50 + identity.discipline * 3,
            tacticalUnderstanding = 50 + identity.discipline * 3,
            fanSupport = 50 + identity.fanaticism * 3,
            confidence = 50,
            grit = 50 + identity.tradition * 2 + identity.discipline * 2,
            form = 50,
            identityAlignment = 70
        )
    }

    private fun extractCity(
        clubName: String
    ): String =
        clubName.substringAfterLast(" ")
}