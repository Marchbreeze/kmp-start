package com.example.kmp.server.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ErrorResponse(
    val error: String,
)

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<IllegalArgumentException> { call, cause ->
            call.respondText(
                Json.encodeToString(ErrorResponse.serializer(), ErrorResponse(cause.message ?: "Bad request")),
                status = HttpStatusCode.BadRequest,
            )
        }
        exception<Throwable> { call, cause ->
            call.respondText(
                Json.encodeToString(ErrorResponse.serializer(), ErrorResponse(cause.message ?: "Internal server error")),
                status = HttpStatusCode.InternalServerError,
            )
        }
    }
}
