package com.recipebook.presentation.route

import com.recipebook.application.plugin.FirebasePrincipal
import com.recipebook.domain.entity.MealType
import com.recipebook.domain.usecase.mealplan.*
import com.recipebook.presentation.dto.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.context.GlobalContext
import java.time.LocalDate
import java.util.UUID

fun Route.mealPlanRoutes() {
    val getMealPlan = GlobalContext.get().get<GetMealPlanUseCase>()
    val addEntry = GlobalContext.get().get<AddMealPlanEntryUseCase>()
    val deleteEntry = GlobalContext.get().get<DeleteMealPlanEntryUseCase>()

    route("/meal-plan") {
        get {
            val principal = call.principal<FirebasePrincipal>()!!
            val from = LocalDate.parse(call.parameters["from"] ?: LocalDate.now().toString())
            val to = LocalDate.parse(call.parameters["to"] ?: LocalDate.now().plusDays(6).toString())
            val entries = getMealPlan(principal.userId, from, to)
            call.respond(entries.map { it.toResponse() })
        }

        post {
            val principal = call.principal<FirebasePrincipal>()!!
            val body = call.receive<AddMealPlanEntryRequest>()
            val entry = addEntry(
                userId = principal.userId,
                recipeId = UUID.fromString(body.recipeId),
                planDate = LocalDate.parse(body.planDate),
                mealType = MealType.valueOf(body.mealType)
            )
            call.respond(HttpStatusCode.Created, entry.toResponse())
        }

        delete("/{id}") {
            val principal = call.principal<FirebasePrincipal>()!!
            val id = UUID.fromString(call.parameters["id"]!!)
            deleteEntry(id, principal.userId)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}
