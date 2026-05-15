package data

import enums.Position

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class Player(
    val playerId: Int,
    val name: String,
    val position: Position,
    val team: String,
    val birthday: LocalDate,
    val placeOfBirth: String,
    val nationality: String,
    val playerStats: PlayerStats,
    val rowPosition: Int,
    val columnPosition: Int
) {

    init {

        require(playerId > 0) {
            "playerId must be greater than 0."
        }

        require(name.isNotBlank()) {
            "name must not be blank."
        }

        require(team.isNotBlank()) {
            "team must not be blank."
        }

        require(placeOfBirth.isNotBlank()) {
            "placeOfBirth must not be blank."
        }

        require(nationality.isNotBlank()) {
            "nationality must not be blank."
        }

        require(rowPosition >= 0) {
            "rowPosition must be >= 0."
        }

        require(columnPosition >= 0) {
            "columnPosition must be >= 0."
        }
    }
}