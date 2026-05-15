package com.recipebook.presentation.route

import com.recipebook.application.plugin.FirebasePrincipal
import com.recipebook.domain.usecase.favorite.*
import com.recipebook.presentation.dto.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.context.GlobalContext
import java.util.UUID

fun Route.favoriteRoutes() {
    val getFavorites = GlobalContext.get().get<GetFavoritesUseCase>()
    val addFavorite = GlobalContext.get().get<AddFavoriteUseCase>()
    val removeFavorite = GlobalContext.get().get<RemoveFavoriteUseCase>()

    route("/favorites") {
        get {
            val principal = call.principal<FirebasePrincipal>()!!
            val page = call.parameters["page"]?.toIntOrNull() ?: 0
            val size = call.parameters["size"]?.toIntOrNull() ?: 20
            val result = getFavorites(principal.userId, page, size)
            call.respond(RecipePageResponse(result.items.map { it.toResponse() }, result.total, result.page, result.size))
        }

        post("/{recipeId}") {
            val principal = call.principal<FirebasePrincipal>()!!
            val recipeId = UUID.fromString(call.parameters["recipeId"]!!)
            addFavorite(principal.userId, recipeId)
            call.respond(HttpStatusCode.NoContent)
        }

        delete("/{recipeId}") {
            val principal = call.principal<FirebasePrincipal>()!!
            val recipeId = UUID.fromString(call.parameters["recipeId"]!!)
            removeFavorite(principal.userId, recipeId)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}
