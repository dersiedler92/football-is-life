package com.fil.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.fil.data.Team
import com.fil.util.loadImageBitmap
import com.fil.world.World

@Composable
fun TeamSelectionScreen(
    world: World,
    onTeamSelected: (Team) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Choose Your Club",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "Your trainer license gives you exactly one shot. Choose carefully."
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(world.teams) { team ->
                TeamSelectionCard(
                    team = team,
                    onClick = { onTeamSelected(team) }
                )
            }
        }
    }
}

@Composable
private fun TeamSelectionCard(
    team: Team,
    onClick: () -> Unit
) {
    val crest = remember(team.pathToCrest) {
        runCatching {
            loadImageBitmap(team.pathToCrest)
        }.getOrNull()
    }

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (crest != null) {
                Image(
                    bitmap = crest,
                    contentDescription = team.name,
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Fit,
                    filterQuality = FilterQuality.High
                )
            } else {
                Text(
                    "No Crest",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = team.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}