package com.example.kmp.server

import com.example.kmp.server.plugins.configureHTTP
import com.example.kmp.server.plugins.configureMonitoring
import com.example.kmp.server.plugins.configureSerialization
import com.example.kmp.server.plugins.configureStatusPages
import com.example.kmp.server.routes.characterRoutes
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module,
    ).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureMonitoring()
    configureStatusPages()

    routing {
        route("/api") {
            characterRoutes()
        }
    }
}
