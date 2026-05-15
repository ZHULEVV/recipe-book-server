package com.recipebook.application.plugin

import com.recipebook.presentation.route.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/health") {
            call.respond(HttpStatusCode.OK, mapOf("status" to "ok"))
        }

        route("/api/v1") {
            ingredientRoutes()
            tagRoutes()
            recipePublicRoutes()

            authenticate("firebase") {
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
