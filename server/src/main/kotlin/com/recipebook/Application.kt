package com.recipebook

import com.recipebook.application.di.repositoryModule
import com.recipebook.application.di.useCaseModule
import com.recipebook.application.firebase.FirebaseAdmin
import com.recipebook.application.plugin.*
import com.recipebook.data.database.DatabaseFactory
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.module() {
    val config = environment.config

    DatabaseFactory.init(
        url = config.property("database.url").getString(),
        driver = config.property("database.driver").getString(),
        maxPoolSize = config.property("database.maxPoolSize").getString().toInt()
    )

    FirebaseAdmin.init(
        credentialsPath = config.propertyOrNull("firebase.credentialsPath")?.getString()
            ?: "firebase-service-account.json"
    )

    install(Koin) {
        slf4jLogger()
        modules(repositoryModule, useCaseModule)
    }

    configureSerialization()
    configureAuthentication()
    configureStatusPages()
    configureHTTP()
    configureRouting()
}
