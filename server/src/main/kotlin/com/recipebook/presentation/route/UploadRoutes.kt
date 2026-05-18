package com.recipebook.presentation.route

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.io.File
import java.util.UUID

@Serializable
data class UploadResponse(val url: String)

fun Route.uploadRoutes(uploadDir: String, baseUrl: String) {
    val dir = File(uploadDir).also { it.mkdirs() }

    route("/upload") {
        post {
            val multipart = call.receiveMultipart()
            var savedUrl: String? = null

            multipart.forEachPart { part ->
                if (part is PartData.FileItem) {
                    val extension = part.originalFileName
                        ?.substringAfterLast(".", "jpg")
                        ?.lowercase()
                        ?.let { if (it in setOf("jpg", "jpeg", "png", "webp")) it else "jpg" }
                        ?: "jpg"
                    val fileName = "${UUID.randomUUID()}.$extension"
                    val file = File(dir, fileName)
                    part.streamProvider().use { input ->
                        file.outputStream().buffered().use { output ->
                            input.copyTo(output)
                        }
                    }
                    savedUrl = "$baseUrl/static/images/$fileName"
                }
                part.dispose()
            }

            val url = savedUrl ?: return@post call.respond(
                HttpStatusCode.BadRequest,
                mapOf("error" to "NO_FILE", "message" to "No file provided")
            )
            call.respond(HttpStatusCode.Created, UploadResponse(url))
        }
    }
}
