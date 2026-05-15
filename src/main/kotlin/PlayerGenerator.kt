import data.Player
import data.PlayerStats
import enums.Position
import kotlinx.datetime.LocalDate
import kotlin.random.Random

object PlayerGenerator {

    fun generate(
        playerId: Int,
        teamName: String,
        nationality: String,
        random: Random
    ): Player {

        val position =
            randomPosition(random)

        val age =
            random.nextInt(17, 35)

        val birthday =
            generateBirthday(age, random)

        val stats =
            generateStats(position, random)

        return Player(
            playerId = playerId,
            name = GermanNameGenerator.generate(random),
            position = position,
            team = teamName,
            birthday = birthday,
            placeOfBirth = "Germany",
            nationality = nationality,
            playerStats = stats,
            rowPosition = 0,
            columnPosition = 0
        )
    }

    private fun randomPosition(
        random: Random
    ): Position {

        val positions = listOf(
            Position.GOALKEEPER,

            Position.LEFT_BACK,
            Position.CENTER_BACK,
            Position.RIGHT_BACK,

            Position.DEFENSIVE_MIDFIELDER,
            Position.CENTRAL_MIDFIELDER,
            Position.ATTACKING_MIDFIELDER,

            Position.LEFT_WINGER,
            Position.RIGHT_WINGER,

            Position.STRIKER
        )

        return positions.random(random)
    }

    private fun generateBirthday(
        age: Int,
        random: Random
    ): LocalDate {

        val year = 2025 - age
        val month = random.nextInt(1, 13)
        val day = random.nextInt(1, 28)

        return LocalDate(
            year,
            month,
            day
        )
    }

    private fun generateStats(
        position: Position,
        random: Random
    ): PlayerStats {

        return when (position) {

            Position.GOALKEEPER ->
                goalkeeperStats(random)

            Position.CENTER_BACK ->
                centerBackStats(random)

            Position.STRIKER ->
                strikerStats(random)

            else ->
                balancedStats(random)
        }
    }

    private fun balancedStats(
        random: Random
    ): PlayerStats {

        return PlayerStats(

            technique = stat(random),
            dribbling = stat(random),
            passing = stat(random),
            finishing = stat(random),
            firstTouch = stat(random),
            crossing = stat(random),

            anticipation = stat(random),
            composure = stat(random),
            vision = stat(random),
            aggression = stat(random),
            leadership = stat(random),
            workRate = stat(random),

            speed = stat(random),
            acceleration = stat(random),
            stamina = stat(random),
            strength = stat(random),
            jumping = stat(random),
            reaction = stat(random),

            tackling = stat(random),
            marking = stat(random),
            blocking = stat(random)
        )
    }

    private fun strikerStats(
        random: Random
    ): PlayerStats {

        return balancedStats(random).copy(
            finishing = highStat(random),
            composure = highStat(random),
            dribbling = highStat(random),
            tackling = lowStat(random),
            marking = lowStat(random)
        )
    }

    private fun centerBackStats(
        random: Random
    ): PlayerStats {

        return balancedStats(random).copy(
            tackling = highStat(random),
            marking = highStat(random),
            strength = highStat(random),
            jumping = highStat(random),
            dribbling = lowStat(random),
            crossing = lowStat(random)
        )
    }

    private fun goalkeeperStats(
        random: Random
    ): PlayerStats {

        return balancedStats(random).copy(
            reaction = highStat(random),
            composure = highStat(random),
            anticipation = highStat(random),
            dribbling = lowStat(random),
            crossing = lowStat(random)
        )
    }

    private fun stat(
        random: Random
    ): Int =
        random.nextInt(35, 76)

    private fun highStat(
        random: Random
    ): Int =
        random.nextInt(65, 96)

    private fun lowStat(
        random: Random
    ): Int =
        random.nextInt(20, 51)
}