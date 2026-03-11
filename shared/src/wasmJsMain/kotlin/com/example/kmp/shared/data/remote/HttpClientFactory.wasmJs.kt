package com.example.kmp.shared.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js

actual fun createPlatformEngine(): HttpClientEngine {
    return Js.create()
}

actual fun getServerBaseUrl(): String = "http://localhost:8080/api/"
