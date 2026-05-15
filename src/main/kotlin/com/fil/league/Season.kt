package com.fil.league

import com.fil.match.Match

data class Season(
    val year: Int,
    val leagueTables: List<LeagueTable>,
    val matches: List<Match>
)