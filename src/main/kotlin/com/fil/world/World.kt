package com.fil.world

import com.fil.data.Club
import com.fil.data.Team
import kotlinx.datetime.LocalDate

data class World(
    val seed: Long,
    val currentDate: LocalDate,
    val clubs: List<Club>,
    val teams: List<Team>
)