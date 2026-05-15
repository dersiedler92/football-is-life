import data.Player
import enums.DuelResult
import enums.PlayerDuelType
import kotlin.math.exp
import kotlin.random.Random

object DuelResolver {

    private const val PLAYER_DUEL_SCALE = 12.0

    fun playerDuel(
        attackingPlayer: Player,
        defendingPlayer: Player,
        duelType: PlayerDuelType,
        random: Random = Random.Default
    ): DuelResult {
        val attackFactor = attackingFactor(attackingPlayer, duelType)
        val defenseFactor = defenseFactor(defendingPlayer, duelType)

        val bias = attackFactor - defenseFactor
        val winProbability = sigmoid(bias / PLAYER_DUEL_SCALE)

        return if (random.nextDouble() < winProbability) {
            DuelResult.VICTORY
        } else {
            DuelResult.DEFEAT
        }
    }

    private fun attackingFactor(
        player: Player,
        duelType: PlayerDuelType
    ): Double =
        when (duelType) {
            PlayerDuelType.ONE_VS_ONE ->
                PlayerFactorCalculator.oneVsOneAttackFactor(player)

            PlayerDuelType.SPRINT ->
                PlayerFactorCalculator.sprintFactor(player)

            PlayerDuelType.AERIAL ->
                PlayerFactorCalculator.aerialAttackFactor(player)

            PlayerDuelType.SHOOT_BLOCK ->
                PlayerFactorCalculator.shootingFactor(player)

            PlayerDuelType.HEAD_BLOCK ->
                PlayerFactorCalculator.aerialAttackFactor(player)

            PlayerDuelType.PHYSICAL ->
                PlayerFactorCalculator.physicalFactor(player)

            PlayerDuelType.PASS_INTERCEPTION ->
                PlayerFactorCalculator.passingFactor(player)

            PlayerDuelType.VS_KEEPER ->
                PlayerFactorCalculator.shootingFactor(player)
        }

    private fun defenseFactor(
        player: Player,
        duelType: PlayerDuelType
    ): Double =
        when (duelType) {
            PlayerDuelType.ONE_VS_ONE ->
                PlayerFactorCalculator.oneVsOneDefenseFactor(player)

            PlayerDuelType.SPRINT ->
                PlayerFactorCalculator.sprintFactor(player)

            PlayerDuelType.AERIAL ->
                PlayerFactorCalculator.aerialDefenseFactor(player)

            PlayerDuelType.SHOOT_BLOCK ->
                PlayerFactorCalculator.blockFactor(player)

            PlayerDuelType.HEAD_BLOCK ->
                PlayerFactorCalculator.blockFactor(player)

            PlayerDuelType.PHYSICAL ->
                PlayerFactorCalculator.physicalFactor(player)

            PlayerDuelType.PASS_INTERCEPTION ->
                PlayerFactorCalculator.interceptionFactor(player)

            PlayerDuelType.VS_KEEPER ->
                PlayerFactorCalculator.goalkeeperOneVsOneFactor(player)
        }

    private fun sigmoid(x: Double): Double =
        1.0 / (1.0 + exp(-x))
}