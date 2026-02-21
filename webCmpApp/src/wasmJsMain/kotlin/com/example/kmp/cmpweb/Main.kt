package com.example.kmp.cmpweb

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.kmp.shared.data.repository.WasmJsSettingsRepository
import com.example.kmp.shared.di.initKoin
import com.example.kmp.shared.domain.repository.SettingsRepository
import kotlinx.browser.document
import org.koin.dsl.module

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin(
        platformModule = module {
            single<SettingsRepository> { WasmJsSettingsRepository() }
        },
    )

    val rootElement = document.getElementById("ComposeTarget")
        ?: error("ComposeTarget element not found in index.html")

    ComposeViewport(rootElement) {
        App()
    }
}
