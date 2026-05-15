package com.fil.league

data class LeagueTableEntry(
    val teamId: Int,
    val played: Int,
    val wins: Int,
    val draws: Int,
    val losses: Int,
    val goalsScored: Int,
    val goalsConceded: Int,
    val points: Int
)