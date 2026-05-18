package com.recipebook.application.plugin

import com.recipebook.presentation.route.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    val baseUrl = environment.config.property("app.baseUrl").getString()
    val uploadDir = environment.config.property("app.uploadDir").getString()

    routing {
        get("/health") {
            call.respond(HttpStatusCode.OK, mapOf("status" to "ok"))
        }

        staticFiles("/static/images", File(uploadDir))

        route("/api/v1") {
            ingredientRoutes()
            tagRoutes()
            recipePublicRoutes()

            authenticate("firebase") {
                uploadRoutes(uploadDir, baseUrl)
                userRoutes()
                recipeAuthRoutes()
                favoriteRoutes()
                commentRoutes()
                ratingRoutes()
                mealPlanRoutes()
            }
        }
    }
}
