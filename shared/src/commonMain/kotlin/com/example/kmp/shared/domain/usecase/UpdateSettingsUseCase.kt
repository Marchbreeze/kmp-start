package com.example.kmp.shared.domain.usecase

import com.example.kmp.shared.domain.repository.SettingsRepository

class UpdateSettingsUseCase(
    private val repository: SettingsRepository,
) {
    suspend fun updateDarkTheme(isDarkTheme: Boolean) {
        repository.updateDarkTheme(isDarkTheme)
    }

    suspend fun updateUseSystemTheme(useSystemTheme: Boolean) {
        repository.updateUseSystemTheme(useSystemTheme)
    }

    suspend fun updateCharactersPerPage(count: Int) {
        repository.updateCharactersPerPage(count)
    }

    suspend fun updateLastViewedCharacterId(characterId: Int) {
        repository.updateLastViewedCharacterId(characterId)
    }
}
