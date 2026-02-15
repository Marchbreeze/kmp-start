package com.example.kmp.shared.data.remote

import com.example.kmp.shared.data.model.AnimeCharacter
import com.example.kmp.shared.data.model.AnimeCharacterResponse
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

    suspend fun getCharacters(page: Int = 1): AnimeCharacterResponse {
        return client
            .get("https://rickandmortyapi.com/api/character?page=$page")
            .body()
    }

    suspend fun getCharacter(id: Int): AnimeCharacter {
        return client
            .get("https://rickandmortyapi.com/api/character/$id")
            .body()
    }
}
