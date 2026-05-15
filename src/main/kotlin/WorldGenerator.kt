import data.World
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

        // Später:
        // val players = PlayerPoolGenerator.generate(random)
        // val leagues = LeagueGenerator.generate(random)
        // val rivalries = RivalryGenerator.generate(random)

        return World(
            seed = seed,
            clubs = clubs,
            teams = teams,
        )
    }
}