package com.fil.data

import kotlinx.serialization.Serializable

@Serializable
data class Club(
    val name: String,
    val city: String,
    val identity: ClubIdentity
)