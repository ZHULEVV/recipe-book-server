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

    private fun buildDataSource(url: String, driver: String, maxPoolSize: Int): DataSource =
        HikariDataSource(HikariConfig().apply {
            jdbcUrl = url
            driverClassName = driver
            maximumPoolSize = maxPoolSize
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        })

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
