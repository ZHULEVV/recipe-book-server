package com.recipebook.presentation.route

import com.recipebook.domain.usecase.ingredient.*
import com.recipebook.presentation.dto.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.koin.core.context.GlobalContext

@Serializable
data class CreateIngredientRequest(val name: String, val defaultUnit: String)

fun Route.ingredientRoutes() {
    val getIngredients = GlobalContext.get().get<GetIngredientsUseCase>()
    val createIngredient = GlobalContext.get().get<CreateIngredientUseCase>()

    route("/ingredients") {
        get {
            call.respond(getIngredients().map { it.toResponse() })
        }

        post {
            val body = call.receive<CreateIngredientRequest>()
            val ingredient = createIngredient(body.name, body.defaultUnit)
            call.respond(HttpStatusCode.Created, ingredient.toResponse())
        }
    }
}
