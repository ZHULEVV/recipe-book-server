package com.recipebook.presentation.route

import com.recipebook.application.plugin.FirebasePrincipal
import com.recipebook.domain.usecase.comment.*
import com.recipebook.presentation.dto.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.context.GlobalContext
import java.util.UUID

fun Route.commentRoutes() {
    val getComments = GlobalContext.get().get<GetCommentsUseCase>()
    val addComment = GlobalContext.get().get<AddCommentUseCase>()
    val deleteComment = GlobalContext.get().get<DeleteCommentUseCase>()

    route("/recipes/{recipeId}/comments") {
        get {
            val recipeId = UUID.fromString(call.parameters["recipeId"]!!)
            val page = call.parameters["page"]?.toIntOrNull() ?: 0
            val size = call.parameters["size"]?.toIntOrNull() ?: 20
            val result = getComments(recipeId, page, size)
            call.respond(CommentPageResponse(result.items.map { it.toResponse() }, result.total, result.page, result.size))
        }

        post {
            val principal = call.principal<FirebasePrincipal>()!!
            val recipeId = UUID.fromString(call.parameters["recipeId"]!!)
            val body = call.receive<AddCommentRequest>()
            val comment = addComment(principal.userId, recipeId, body.text)
            call.respond(HttpStatusCode.Created, comment.toResponse())
        }
    }

    delete("/comments/{commentId}") {
        val principal = call.principal<FirebasePrincipal>()!!
        val commentId = UUID.fromString(call.parameters["commentId"]!!)
        deleteComment(commentId, principal.userId)
        call.respond(HttpStatusCode.NoContent)
    }
}
