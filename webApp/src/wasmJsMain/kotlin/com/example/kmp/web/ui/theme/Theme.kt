package com.example.kmp.web.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.example.kmp.shared.presentation.viewmodel.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBBC3FF),
    onPrimary = Color(0xFF1B2678),
    primaryContainer = Color(0xFF333E90),
    secondary = Color(0xFFBEC6DC),
    surface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFF2B2930),
    onSurface = Color(0xFFE6E1E6),
    onSurfaceVariant = Color(0xFFC9C5CA),
    error = Color(0xFFFFB4AB),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4B56A9),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFDEE0FF),
    secondary = Color(0xFF5B5D72),
    surface = Color(0xFFFFFBFF),
    surfaceVariant = Color(0xFFE4E1EC),
    onSurface = Color(0xFF1C1B1F),
    onSurfaceVariant = Color(0xFF47464F),
    error = Color(0xFFBA1A1A),
)

@Composable
fun WebAppTheme(
    content: @Composable () -> Unit,
) {
    val settingsViewModel: SettingsViewModel = koinViewModel()
    val settings by settingsViewModel.settings.collectAsState()

    val colorScheme = if (settings.isDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}