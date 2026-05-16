package com.fil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fil.database.DatabaseFactory
import com.fil.database.SaveGameRepository
import com.fil.world.GameCreator
import com.fil.world.World
import kotlin.random.Random

@Composable
fun NewGameScreen() {

    var saveName by remember {
        mutableStateOf("My First Club Story")
    }

    var seedText by remember {
        mutableStateOf(Random.nextLong().toString())
    }

    var generatedWorld by remember {
        mutableStateOf<World?>(null)
    }

    if (generatedWorld == null) {

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                "New Game",
                style = MaterialTheme.typography.headlineMedium
            )

            OutlinedTextField(
                value = saveName,
                onValueChange = { saveName = it },
                label = { Text("Save name") }
            )

            OutlinedTextField(
                value = seedText,
                onValueChange = { seedText = it },
                label = { Text("World seed") }
            )

            Button(
                onClick = {

                    val seed =
                        seedText.toLongOrNull()
                            ?: Random.nextLong()

                    generatedWorld =
                        GameCreator.createNewGame(seed)
                }
            ) {
                Text("Generate World")
            }
        }

    } else {

        TeamSelectionScreen(
            world = generatedWorld!!,
            onTeamSelected = { selectedTeam ->

                val database =
                    DatabaseFactory.createDatabase()

                val repository =
                    SaveGameRepository(database)

                val saveGameId = repository.persistNewWorld(
                    saveName = saveName,
                    world = generatedWorld!!
                )

                println("Selected Team: ${selectedTeam.name}")
            }
        )
    }
}