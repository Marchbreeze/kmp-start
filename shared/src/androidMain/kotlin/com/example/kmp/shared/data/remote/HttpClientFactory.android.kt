package com.example.kmp.shared.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit

actual fun createPlatformEngine(): HttpClientEngine {
    return OkHttp.create {
        config {
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }
    }
}

actual fun getServerBaseUrl(): String = "http://10.0.2.2:8080/api/"
