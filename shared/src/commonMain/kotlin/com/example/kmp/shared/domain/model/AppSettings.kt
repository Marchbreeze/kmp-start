package com.example.kmp.shared.domain.model

data class AppSettings(
    val isDarkTheme: Boolean = false,
    val useSystemTheme: Boolean = true,
    val charactersPerPage: Int = 20,
    val lastViewedCharacterId: Int? = null,
)
