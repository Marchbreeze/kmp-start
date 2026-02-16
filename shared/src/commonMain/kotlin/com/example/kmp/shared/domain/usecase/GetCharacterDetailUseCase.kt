package com.example.kmp.shared.domain.usecase

import com.example.kmp.shared.data.model.AnimeCharacter
import com.example.kmp.shared.domain.repository.CharacterRepository

class GetCharacterDetailUseCase(
    private val repository: CharacterRepository,
) {
    suspend operator fun invoke(id: Int): AnimeCharacter {
        return repository.getCharacter(id)
    }
}