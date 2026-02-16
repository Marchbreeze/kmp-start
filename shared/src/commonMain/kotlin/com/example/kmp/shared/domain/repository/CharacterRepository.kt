package com.example.kmp.shared.domain.repository

import com.example.kmp.shared.data.model.AnimeCharacter
import com.example.kmp.shared.data.model.AnimeCharacterResponse

interface CharacterRepository {
    suspend fun getCharacters(page: Int = 1): AnimeCharacterResponse
    suspend fun getCharacter(id: Int): AnimeCharacter
}