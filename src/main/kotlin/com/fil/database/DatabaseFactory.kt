package com.fil.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.io.File

object DatabaseFactory {

    private const val DATABASE_FILE_NAME = "football_is_life.db"

    fun createDatabase(
        resetDatabase: Boolean = false
    ): FootballDatabase {
        val databaseFile = File(DATABASE_FILE_NAME)

        if (resetDatabase && databaseFile.exists()) {
            databaseFile.delete()
        }

        val driver: SqlDriver =
            JdbcSqliteDriver("jdbc:sqlite:$DATABASE_FILE_NAME")

        if (!databaseFile.exists()) {
            FootballDatabase.Schema.create(driver)
        }

        return FootballDatabase(driver)
    }
}