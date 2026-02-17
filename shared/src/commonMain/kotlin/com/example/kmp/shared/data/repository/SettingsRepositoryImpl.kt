package com.example.kmp.shared.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.kmp.shared.domain.model.AppSettings
import com.example.kmp.shared.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : SettingsRepository {

    private object PreferencesKeys {
        val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
        val USE_SYSTEM_THEME = booleanPreferencesKey("use_system_theme")
        val CHARACTERS_PER_PAGE = intPreferencesKey("characters_per_page")
        val LAST_VIEWED_CHARACTER_ID = intPreferencesKey("last_viewed_character_id")
    }

    override fun getSettings(): Flow<AppSettings> {
        return dataStore.data.map { preferences ->
            AppSettings(
                isDarkTheme = preferences[PreferencesKeys.IS_DARK_THEME] ?: false,
                useSystemTheme = preferences[PreferencesKeys.USE_SYSTEM_THEME] ?: true,
                charactersPerPage = preferences[PreferencesKeys.CHARACTERS_PER_PAGE] ?: 20,
                lastViewedCharacterId = preferences[PreferencesKeys.LAST_VIEWED_CHARACTER_ID],
            )
        }
    }

    override suspend fun updateDarkTheme(isDarkTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_THEME] = isDarkTheme
        }
    }

    override suspend fun updateUseSystemTheme(useSystemTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USE_SYSTEM_THEME] = useSystemTheme
        }
    }

    override suspend fun updateCharactersPerPage(count: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.CHARACTERS_PER_PAGE] = count
        }
    }

    override suspend fun updateLastViewedCharacterId(characterId: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_VIEWED_CHARACTER_ID] = characterId
        }
    }
}
