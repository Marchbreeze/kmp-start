package com.example.kmp.shared.data.repository

import com.example.kmp.shared.domain.model.AppSettings
import com.example.kmp.shared.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Minimal in-memory SettingsRepository for JS platform.
 * Web version uses only List/Detail screens, so settings persistence is not required.
 */
class JsSettingsRepository : SettingsRepository {

    private val settingsFlow = MutableStateFlow(AppSettings())

    override fun getSettings(): Flow<AppSettings> = settingsFlow

    override suspend fun updateDarkTheme(isDarkTheme: Boolean) {
        settingsFlow.value = settingsFlow.value.copy(isDarkTheme = isDarkTheme)
    }

    override suspend fun updateUseSystemTheme(useSystemTheme: Boolean) {
        settingsFlow.value = settingsFlow.value.copy(useSystemTheme = useSystemTheme)
    }

    override suspend fun updateCharactersPerPage(count: Int) {
        settingsFlow.value = settingsFlow.value.copy(charactersPerPage = count)
    }

    override suspend fun updateLastViewedCharacterId(characterId: Int) {
        settingsFlow.value = settingsFlow.value.copy(lastViewedCharacterId = characterId)
    }
}
