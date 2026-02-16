package com.example.kmp.shared.data.remote

import com.example.kmp.shared.data.model.AnimeCharacter
import com.example.kmp.shared.data.model.AnimeCharacterResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RickAndMortyApi(
    private val client: HttpClient,
) {

    suspend fun getCharacters(page: Int = 1): AnimeCharacterResponse {
        return client
            .get("character?page=$page")
            .body()
    }

    suspend fun getCharacter(id: Int): AnimeCharacter {
        return client
            .get("character/$id")
            .body()
    }
}