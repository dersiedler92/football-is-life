package com.fil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fil.database.DatabaseFactory
import com.fil.database.SaveGameRepository
import com.fil.world.GameCreator
import kotlin.random.Random

@Composable
fun NewGameScreen() {
    var saveName by remember { mutableStateOf("My First Club Story") }
    var seedText by remember { mutableStateOf(Random.nextLong().toString()) }
    var statusText by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.width(520.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "New Game",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "Create a seeded football world with generated clubs, teams and players."
        )

        OutlinedTextField(
            value = saveName,
            onValueChange = { saveName = it },
            label = { Text("Save name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = seedText,
            onValueChange = { seedText = it },
            label = { Text("World seed") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    seedText = Random.nextLong().toString()
                    statusText = null
                }
            ) {
                Text("Randomize Seed")
            }

            Button(
                onClick = {
                    val seed = seedText.toLongOrNull()

                    if (seed == null) {
                        statusText = "Invalid seed. Use a whole number."
                        return@Button
                    }

                    val world = GameCreator.createNewGame(seed)
                    val database = DatabaseFactory.createDatabase(resetDatabase = true)
                    val repository = SaveGameRepository(database)

                    repository.persistNewWorld(
                        saveGameId = 1L,
                        saveName = saveName.ifBlank { "Unnamed Save" },
                        world = world
                    )

                    statusText =
                        "Created world with ${world.teams.size} teams and ${world.teams.sumOf { it.squad.size }} players."
                }
            ) {
                Text("Create World")
            }
        }

        statusText?.let {
            Text(it)
        }
    }
}