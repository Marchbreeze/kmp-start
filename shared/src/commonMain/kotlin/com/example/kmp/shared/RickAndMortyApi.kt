package com.example.kmp.shared

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RickAndMortyApi {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                },
            )
        }
    }

    suspend fun getCharacters(page: Int = 1): CharacterResponse {
        return client
            .get("https://rickandmortyapi.com/api/character?page=$page")
            .body()
    }
}
