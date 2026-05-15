import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.io.BufferedInputStream
import javax.imageio.ImageIO
import kotlin.random.Random

fun main() = application {

    val windowState = rememberWindowState(
        placement = WindowPlacement.Maximized
    )

    Window(
        onCloseRequest = {
            // Intentionally blocked.
            // Use the in-game Quit Game button instead.
        },
        title = "Football Is Life",
        state = windowState,
        undecorated = true,
        resizable = false
    ) {
        App(
            onQuitGame = ::exitApplication
        )
    }
}

@Composable
@Preview
fun App(
    onQuitGame: () -> Unit
) {

    LaunchedEffect(Unit) {
        BgmPlayer.playLoop("/bgm/opening.wav")
    }

    val backgroundImage = remember {
        loadImageBitmap("/images/title.png")
    }


    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                bitmap = backgroundImage,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.65f)
            ) {
                MainScreen(
                    onQuitGame = onQuitGame
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    onQuitGame: () -> Unit
) {

    var selectedMenu by remember {
        mutableStateOf(MainMenu.NEW_GAME)
    }

    Row(
        modifier = Modifier.fillMaxSize()
    ) {

        Sidebar(
            selected = selectedMenu,
            onSelect = { selectedMenu = it },
            onQuitGame = onQuitGame
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            when (selectedMenu) {

                MainMenu.NEW_GAME -> NewGameScreen()

                MainMenu.SETTINGS -> SettingsScreen()

                MainMenu.ABOUT -> AboutScreen()
            }
        }
    }
}

enum class MainMenu(
    val label: String
) {

    NEW_GAME("New Game"),

    SETTINGS("Settings"),

    ABOUT("About")
}

@Composable
fun Sidebar(
    selected: MainMenu,
    onSelect: (MainMenu) -> Unit,
    onQuitGame: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.88f),
        tonalElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                text = "Football is Life",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Manager Console",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            MainMenu.entries.forEach { item ->
                NavigationItem(
                    label = item.label,
                    selected = item == selected,
                    onClick = { onSelect(item) }
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = onQuitGame,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Quit Game")
            }
        }
    }
}

@Composable
fun NavigationItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = MaterialTheme.shapes.large,
        color = if (selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surface.copy(alpha = 0.0f)
        },
        contentColor = if (selected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.78f)
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(label)
        }
    }
}

@Composable
fun NewGameScreen() {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            "New Game",
            style = MaterialTheme.typography.headlineMedium
        )

        val world = remember {
            GameCreator.createNewGame()
        }

        val random = Random(world.seed)

        val names = remember {
            List(10) {
                GermanNameGenerator.generate(random)
            }
        }

        names.forEach { name ->
            Text(name)
        }

        Text ("Der World-Seed lautet: " + world.seed)
    }
}

@Composable
fun SettingsScreen() {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            "Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        listOf(
            "Emir Kaya - ZM - 62%",
            "Luis Ferreira - ST - 58%",
            "Jonas Eberhardt - TW - 66%"
        ).forEach { player ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = player,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun AboutScreen() {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            "About Football Is Life",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "Football Is Life ist ein storygetriebener Fußballmanager über Vereinsidentität, Aufstieg, Loyalität und dramatische Fußballmomente."
        )

        Text(
            "Version: 1.0.0"
        )

        Text(
            "Built with Kotlin + Compose Desktop."
        )
    }
}

fun loadImageBitmap(resourcePath: String) =
    object {}::class.java
        .getResourceAsStream(resourcePath)
        ?.let { BufferedInputStream(it) }
        ?.use { ImageIO.read(it) }
        ?.toComposeImageBitmap()
        ?: error("Could not load image: $resourcePath")