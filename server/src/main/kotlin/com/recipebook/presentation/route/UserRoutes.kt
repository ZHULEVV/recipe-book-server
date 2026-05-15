package com.recipebook.presentation.route

import com.recipebook.application.plugin.FirebasePrincipal
import com.recipebook.domain.usecase.user.*
import com.recipebook.presentation.dto.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.context.GlobalContext
import java.util.UUID

fun Route.userRoutes() {
    val getUserProfile = GlobalContext.get().get<GetUserProfileUseCase>()
    val updateUserProfile = GlobalContext.get().get<UpdateUserProfileUseCase>()
    val getExcludedIngredients = GlobalContext.get().get<GetExcludedIngredientsUseCase>()
    val addExcludedIngredient = GlobalContext.get().get<AddExcludedIngredientUseCase>()
    val removeExcludedIngredient = GlobalContext.get().get<RemoveExcludedIngredientUseCase>()

    route("/users/me") {
        get {
            val principal = call.principal<FirebasePrincipal>()!!
            val user = getUserProfile(principal.firebaseUid)
            call.respond(user.toResponse())
        }

        put {
            val principal = call.principal<FirebasePrincipal>()!!
            val body = call.receive<UpdateUserRequest>()
            val user = updateUserProfile(principal.userId, body.displayName)
            call.respond(user.toResponse())
        }

        route("/excluded-ingredients") {
            get {
                val principal = call.principal<FirebasePrincipal>()!!
                val ingredients = getExcludedIngredients(principal.userId)
                call.respond(ingredients.map { it.toResponse() })
            }

            post("/{ingredientId}") {
                val principal = call.principal<FirebasePrincipal>()!!
                val ingredientId = UUID.fromString(call.parameters["ingredientId"]!!)
                addExcludedIngredient(principal.userId, ingredientId)
                call.respond(HttpStatusCode.NoContent)
            }

            delete("/{ingredientId}") {
                val principal = call.principal<FirebasePrincipal>()!!
                val ingredientId = UUID.fromString(call.parameters["ingredientId"]!!)
                removeExcludedIngredient(principal.userId, ingredientId)
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}
