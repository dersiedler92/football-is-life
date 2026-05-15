package com.fil.match

import com.fil.enums.MatchStatus
import kotlinx.datetime.LocalDateTime

data class Match(
    val matchId: Int,
    val homeTeamId: Int,
    val awayTeamId: Int,
    val competitionId: Int,
    val matchDay: Int,
    val kickoffTime: LocalDateTime,
    val status: MatchStatus,
    val result: MatchResult?
)