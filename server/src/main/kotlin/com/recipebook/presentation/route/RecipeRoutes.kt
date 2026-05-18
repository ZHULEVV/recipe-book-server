package com.recipebook.presentation.route

import com.recipebook.application.plugin.FirebasePrincipal
import com.recipebook.domain.entity.Difficulty
import com.recipebook.domain.usecase.recipe.*
import com.recipebook.presentation.dto.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.context.GlobalContext
import java.util.UUID

fun Route.recipePublicRoutes() {
    val getRecipes = GlobalContext.get().get<GetRecipesUseCase>()
    val searchRecipes = GlobalContext.get().get<SearchRecipesUseCase>()
    val getRecipeById = GlobalContext.get().get<GetRecipeByIdUseCase>()

    route("/recipes") {
        get {
            val page = call.parameters["page"]?.toIntOrNull() ?: 0
            val size = call.parameters["size"]?.toIntOrNull() ?: 20
            val difficulty = call.parameters["difficulty"]?.let { runCatching { Difficulty.valueOf(it) }.getOrNull() }
            val tagIds = call.parameters.getAll("tagId")?.mapNotNull { runCatching { UUID.fromString(it) }.getOrNull() } ?: emptyList()
            val result = getRecipes(page, size, difficulty, tagIds)
            call.respond(RecipePageResponse(result.items.map { it.toResponse() }, result.total, result.page, result.size))
        }

        get("/search") {
            val query = call.parameters["query"] ?: ""
            val page = call.parameters["page"]?.toIntOrNull() ?: 0
            val size = call.parameters["size"]?.toIntOrNull() ?: 20
            val result = searchRecipes(query, page, size)
            call.respond(RecipePageResponse(result.items.map { it.toResponse() }, result.total, result.page, result.size))
        }

        get("/{id}") {
            val id = UUID.fromString(call.parameters["id"]!!)
            val recipe = getRecipeById(id)
            call.respond(recipe.toResponse())
        }
    }
}

fun Route.recipeAuthRoutes() {
    val getMyRecipes = GlobalContext.get().get<GetMyRecipesUseCase>()
    val createRecipe = GlobalContext.get().get<CreateRecipeUseCase>()
    val updateRecipe = GlobalContext.get().get<UpdateRecipeUseCase>()
    val deleteRecipe = GlobalContext.get().get<DeleteRecipeUseCase>()
    val publishRecipe = GlobalContext.get().get<PublishRecipeUseCase>()

    route("/recipes") {
        get("/my") {
            val principal = call.principal<FirebasePrincipal>()!!
            val page = call.parameters["page"]?.toIntOrNull() ?: 0
            val size = call.parameters["size"]?.toIntOrNull() ?: 20
            val result = getMyRecipes(principal.userId, page, size)
            call.respond(RecipePageResponse(result.items.map { it.toResponse() }, result.total, result.page, result.size))
        }

        post {
            val principal = call.principal<FirebasePrincipal>()!!
            val body = call.receive<CreateRecipeRequest>()
            val recipe = createRecipe(body.toDomain(principal.userId))
            call.respond(HttpStatusCode.Created, recipe.toResponse())
        }

        put("/{id}") {
            val principal = call.principal<FirebasePrincipal>()!!
            val id = UUID.fromString(call.parameters["id"]!!)
            val body = call.receive<UpdateRecipeRequest>()
            val recipe = updateRecipe(id, principal.userId, body.toDomain())
            call.respond(recipe.toResponse())
        }

        delete("/{id}") {
            val principal = call.principal<FirebasePrincipal>()!!
            val id = UUID.fromString(call.parameters["id"]!!)
            deleteRecipe(id, principal.userId)
            call.respond(HttpStatusCode.NoContent)
        }

        post("/{id}/publish") {
            val principal = call.principal<FirebasePrincipal>()!!
            val id = UUID.fromString(call.parameters["id"]!!)
            val recipe = publishRecipe(id, principal.userId)
            call.respond(recipe.toResponse())
        }
    }
}
