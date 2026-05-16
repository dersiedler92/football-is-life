package com.fil.data

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val teamId: Int,
    val name: String,
    val city: String,
    val country: String,
    val pathToCrest: String,
    val squad: List<Player>,
    val teamStats: TeamStats
) {

    init {

        require(teamId > 0) {
            "teamId must be greater than 0."
        }

        require(name.isNotBlank()) {
            "name must not be blank."
        }

        require(city.isNotBlank()) {
            "city must not be blank."
        }

        require(country.isNotBlank()) {
            "country must not be blank."
        }

        require(squad.isNotEmpty()) {
            "squad must not be empty."
        }
    }
}