package com.example.kmp.shared.domain.repository

import com.example.kmp.shared.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSettings(): Flow<AppSettings>
    suspend fun updateDarkTheme(isDarkTheme: Boolean)
    suspend fun updateUseSystemTheme(useSystemTheme: Boolean)
    suspend fun updateCharactersPerPage(count: Int)
    suspend fun updateLastViewedCharacterId(characterId: Int)
}
