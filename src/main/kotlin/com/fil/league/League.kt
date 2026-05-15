package com.fil.league

import com.fil.data.Team
import kotlinx.serialization.Serializable

@Serializable
data class League(
    val leagueId: Int,
    val name: String,
    val country: String,
    val level: Int,
    val teams: List<Team>
) {

    init {
        require(leagueId > 0)
        require(name.isNotBlank())
        require(country.isNotBlank())
        require(level > 0)
        require(teams.isNotEmpty())
    }
}