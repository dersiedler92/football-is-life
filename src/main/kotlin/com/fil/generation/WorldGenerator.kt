package com.fil.generation

import com.fil.content.ClubLoader
import com.fil.world.World
import kotlinx.datetime.LocalDate
import kotlin.random.Random

class WorldGenerator(
    private val seed: Long
) {

    private val random = Random(seed)

    fun generate(): World {
        val clubs = ClubLoader.loadGermanFirstDivision()

        var nextPlayerId = 1

        val teams = clubs.mapIndexed { index, club ->
            val team = TeamGenerator.generateTeam(
                teamId = index + 1,
                club = club,
                firstPlayerId = nextPlayerId,
                random = random
            )

            nextPlayerId += team.squad.size

            team
        }

        return World(
            seed = seed,
            currentDate = LocalDate(2025, 7, 1),
            clubs = clubs,
            teams = teams,
        )
    }
}