package com.example.kmp.server.routes

import com.example.kmp.server.data.CharacterDatabase
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlinx.serialization.json.Json

fun Route.characterRoutes() {
    route("/character") {
        get {
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
            val response = CharacterDatabase.getCharacters(page)
            call.respond(response)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respondText(
                    Json.encodeToString(
                        com.example.kmp.server.plugins.ErrorResponse.serializer(),
                        com.example.kmp.server.plugins.ErrorResponse("Invalid character ID"),
                    ),
                    status = HttpStatusCode.BadRequest,
                )

            val character = CharacterDatabase.getCharacter(id)
                ?: return@get call.respondText(
                    Json.encodeToString(
                        com.example.kmp.server.plugins.ErrorResponse.serializer(),
                        com.example.kmp.server.plugins.ErrorResponse("Character not found"),
                    ),
                    status = HttpStatusCode.NotFound,
                )

            call.respond(character)
        }
    }
}
