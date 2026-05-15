import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "2.1.20"
    id("org.jetbrains.compose") version "1.8.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.20"
    id("app.cash.sqldelight") version "2.0.2"
}

group = "com.fil"
version = "1.0.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    implementation("org.jetbrains.compose.components:components-resources:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.3")
    implementation("com.charleskorn.kaml:kaml:0.61.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
    implementation("org.xerial:sqlite-jdbc:3.45.1.0")
    implementation("org.slf4j:slf4j-simple:2.0.13")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

sqldelight {
    databases {
        create("FootballDatabase") {
            packageName.set("com.fil.database")
        }
    }
}

tasks.matching { it.name == "verifyMainFootballDatabaseMigration" }.configureEach {
    enabled = false
}

compose.desktop {
    application {
        mainClass = "com.fil.MainKt"

        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Msi,
                TargetFormat.Deb
            )

            packageName = "Football is Life"
            packageVersion = "1.0.0"
        }
    }
}