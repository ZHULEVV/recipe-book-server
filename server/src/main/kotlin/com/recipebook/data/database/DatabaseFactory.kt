package com.recipebook.data.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import javax.sql.DataSource

object DatabaseFactory {
    fun init(url: String, driver: String, maxPoolSize: Int) {
        val dataSource = buildDataSource(url, driver, maxPoolSize)
        runMigrations(dataSource)
        Database.connect(dataSource)
    }

    private fun buildDataSource(url: String, driver: String, maxPoolSize: Int): DataSource {
        val normalized = if (url.startsWith("jdbc:")) url else "jdbc:$url"
        val credentialsRegex = Regex("(jdbc:postgresql://)([^:@]+):([^@]+)@(.+)")
        val match = credentialsRegex.matchEntire(normalized)
        return HikariDataSource(HikariConfig().apply {
            if (match != null) {
                jdbcUrl = "${match.groupValues[1]}${match.groupValues[4]}"
                username = match.groupValues[2]
                password = match.groupValues[3]
            } else {
                jdbcUrl = normalized
            }
            driverClassName = driver
            maximumPoolSize = maxPoolSize
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        })
    }

    private fun runMigrations(dataSource: DataSource) {
        Flyway.configure()
            .dataSource(dataSource)
            .locations("classpath:db/migration")
            .load()
            .migrate()
    }
}

suspend fun <T> dbQuery(block: () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }
