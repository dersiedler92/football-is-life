package com.fil.data

import kotlinx.serialization.Serializable

@Serializable
data class ClubFile(
    val clubs: List<Club>
)