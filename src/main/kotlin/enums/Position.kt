package enums

import kotlinx.serialization.Serializable

@Serializable
enum class Position {

    GOALKEEPER,

    LEFT_BACK,
    CENTER_BACK,
    RIGHT_BACK,

    DEFENSIVE_MIDFIELDER,
    CENTRAL_MIDFIELDER,
    ATTACKING_MIDFIELDER,

    LEFT_WINGER,
    RIGHT_WINGER,

    STRIKER
}