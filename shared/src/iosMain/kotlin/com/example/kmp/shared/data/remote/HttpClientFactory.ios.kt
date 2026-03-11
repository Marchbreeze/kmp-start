package com.example.kmp.shared.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun createPlatformEngine(): HttpClientEngine {
    return Darwin.create {
        configureRequest {
            setTimeoutInterval(30.0)
        }
    }
}

actual fun getServerBaseUrl(): String = "http://localhost:8080/api/"
