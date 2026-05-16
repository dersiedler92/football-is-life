package com.fil.database

import com.fil.data.Player
import com.fil.data.Team
import com.fil.util.JsonProvider
import com.fil.world.World
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString

class SaveGameRepository(
    private val database: FootballDatabase
) {

    fun persistNewWorld(
        saveName: String,
        world: World
    ): Long {
        val now = Clock.System.now().toString()

        return database.transactionWithResult {
            database.schemaQueries.insertSaveGame(
                name = saveName,
                seed = world.seed,
                created_at = now,
                updated_at = now,
                current_date = world.currentDate.toString(),
                world_json = JsonProvider.json.encodeToString(world)
            )

            val saveGameId =
                database.schemaQueries
                    .lastInsertRowId()
                    .executeAsOne()

            world.teams.forEach { team ->
                insertTeam(saveGameId, team)

                team.squad.forEach { player ->
                    insertPlayer(saveGameId, team.teamId.toLong(), player)
                }
            }

            saveGameId
        }
    }

    private fun insertTeam(
        saveGameId: Long,
        team: Team
    ) {
        database.schemaQueries.insertTeam(
            team_id = team.teamId.toLong(),
            save_game_id = saveGameId,
            team_json = JsonProvider.json.encodeToString(team)
        )
    }

    private fun insertPlayer(
        saveGameId: Long,
        teamId: Long,
        player: Player
    ) {
        database.schemaQueries.insertPlayer(
            player_id = player.playerId.toLong(),
            save_game_id = saveGameId,
            team_id = teamId,
            player_json = JsonProvider.json.encodeToString(player)
        )
    }
}