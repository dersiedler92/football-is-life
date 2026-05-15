package data

import kotlinx.serialization.Serializable

@Serializable
data class Club(
    val name: String,
    val identity: ClubIdentity
)