package com.example.kmp.shared.data.repository

import com.example.kmp.shared.data.model.AnimeCharacter
import com.example.kmp.shared.data.model.AnimeCharacterResponse
import com.example.kmp.shared.data.remote.RickAndMortyApi
import com.example.kmp.shared.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): AnimeCharacterResponse {
        return api.getCharacters(page)
    }

    override suspend fun getCharacter(id: Int): AnimeCharacter {
        return api.getCharacter(id)
    }
}