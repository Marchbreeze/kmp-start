package com.example.kmp.web

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.kmp.web.ui.screens.CharacterDetailScreen
import com.example.kmp.web.ui.screens.CharacterListScreen
import com.example.kmp.web.ui.screens.SettingsScreen
import com.example.kmp.web.ui.theme.WebAppTheme

sealed class Screen {
    data object CharacterList : Screen()
    data class CharacterDetail(val characterId: Int) : Screen()
    data object Settings : Screen()
}

@Composable
fun App() {
    var currentScreen: Screen by remember { mutableStateOf(Screen.CharacterList) }

    WebAppTheme {
        Crossfade(targetState = currentScreen) { screen ->
            when (screen) {
                is Screen.CharacterList -> {
                    CharacterListScreen(
                        onCharacterClick = { characterId ->
                            currentScreen = Screen.CharacterDetail(characterId)
                        },
                        onSettingsClick = {
                            currentScreen = Screen.Settings
                        },
                    )
                }
                is Screen.CharacterDetail -> {
                    CharacterDetailScreen(
                        characterId = screen.characterId,
                        onBackClick = {
                            currentScreen = Screen.CharacterList
                        },
                    )
                }
                is Screen.Settings -> {
                    SettingsScreen(
                        onBackClick = {
                            currentScreen = Screen.CharacterList
                        },
                    )
                }
            }
        }
    }
}