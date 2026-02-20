package com.example.kmp.cmpweb

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.kmp.cmpweb.ui.screens.CharacterDetailScreen
import com.example.kmp.cmpweb.ui.screens.CharacterListScreen

sealed class Screen {
    data object List : Screen()
    data class Detail(val characterId: Int) : Screen()
}

@Composable
fun App() {
    val colorScheme = darkColorScheme()

    MaterialTheme(colorScheme = colorScheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            var currentScreen by remember { mutableStateOf<Screen>(Screen.List) }

            when (val screen = currentScreen) {
                is Screen.List -> {
                    CharacterListScreen(
                        onCharacterClick = { characterId ->
                            currentScreen = Screen.Detail(characterId)
                        },
                    )
                }
                is Screen.Detail -> {
                    CharacterDetailScreen(
                        characterId = screen.characterId,
                        onBackClick = {
                            currentScreen = Screen.List
                        },
                    )
                }
            }
        }
    }
}