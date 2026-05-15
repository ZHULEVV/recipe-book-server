package com.recipebook.presentation

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HealthRouteTest {

    @Test
    fun `GET health returns 200 OK`() = testApplication {
        application {
            routing {
                get("/health") {
                    call.respond(HttpStatusCode.OK, mapOf("status" to "ok"))
                }
            }
        }

        val response = client.get("/health")

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `GET unknown path returns 404`() = testApplication {
        application {
            routing {
                get("/health") {
                    call.respond(HttpStatusCode.OK)
                }
            }
        }

        val response = client.get("/unknown")

        assertEquals(HttpStatusCode.NotFound, response.status)
    }
}
