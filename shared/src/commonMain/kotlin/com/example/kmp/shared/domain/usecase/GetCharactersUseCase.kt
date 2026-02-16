package com.example.kmp.shared.domain.usecase

import com.example.kmp.shared.data.model.AnimeCharacterResponse
import com.example.kmp.shared.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val repository: CharacterRepository,
) {
    suspend operator fun invoke(page: Int = 1): AnimeCharacterResponse {
        return repository.getCharacters(page)
    }
}