package com.recipebook.presentation.route

import com.recipebook.domain.usecase.tag.GetTagsUseCase
import com.recipebook.presentation.dto.toResponse
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.context.GlobalContext

fun Route.tagRoutes() {
    val getTags = GlobalContext.get().get<GetTagsUseCase>()

    get("/tags") {
        call.respond(getTags().map { it.toResponse() })
    }
}
