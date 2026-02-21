package com.example.kmp.cmpweb

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.kmp.cmpweb.ui.screens.CharacterDetailScreen
import com.example.kmp.cmpweb.ui.screens.CharacterListScreen
import kotlinx.browser.window

sealed class Screen {
    data object List : Screen()
    data class Detail(val characterId: Int) : Screen()
}

private fun parseHash(hash: String): Screen {
    val path = hash.removePrefix("#")
    if (path.startsWith("detail/")) {
        val id = path.removePrefix("detail/").toIntOrNull()
        if (id != null) return Screen.Detail(id)
    }
    return Screen.List
}

@Composable
fun App() {
    val colorScheme = darkColorScheme()

    MaterialTheme(colorScheme = colorScheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            var currentScreen by remember { mutableStateOf(parseHash(window.location.hash)) }

            // 브라우저 뒤로가기/앞으로가기 감지
            DisposableEffect(Unit) {
                val listener: (org.w3c.dom.events.Event) -> Unit = {
                    currentScreen = parseHash(window.location.hash)
                }
                window.addEventListener("popstate", listener)
                onDispose {
                    window.removeEventListener("popstate", listener)
                }
            }

            when (val screen = currentScreen) {
                is Screen.List -> {
                    CharacterListScreen(
                        onCharacterClick = { characterId ->
                            window.history.pushState(null, "", "#detail/$characterId")
                            currentScreen = Screen.Detail(characterId)
                        },
                    )
                }
                is Screen.Detail -> {
                    CharacterDetailScreen(
                        characterId = screen.characterId,
                        onBackClick = {
                            window.history.back()
                        },
                    )
                }
            }
        }
    }
}
