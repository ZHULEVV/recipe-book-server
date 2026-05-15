package com.recipebook.presentation.route

import com.recipebook.application.plugin.FirebasePrincipal
import com.recipebook.domain.usecase.rating.*
import com.recipebook.presentation.dto.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.context.GlobalContext
import java.util.UUID

fun Route.ratingRoutes() {
    val getRating = GlobalContext.get().get<GetRecipeRatingUseCase>()
    val rateRecipe = GlobalContext.get().get<RateRecipeUseCase>()

    route("/recipes/{recipeId}/rating") {
        get {
            val recipeId = UUID.fromString(call.parameters["recipeId"]!!)
            call.respond(getRating(recipeId).toResponse())
        }

        post {
            val principal = call.principal<FirebasePrincipal>()!!
            val recipeId = UUID.fromString(call.parameters["recipeId"]!!)
            val body = call.receive<RateRequest>()
            val rating = rateRecipe(principal.userId, recipeId, body.score)
            call.respond(RatingResponse(averageScore = rating.score.toDouble(), count = 1))
        }
    }
}
