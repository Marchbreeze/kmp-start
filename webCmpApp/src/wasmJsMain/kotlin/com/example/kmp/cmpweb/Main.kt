package com.example.kmp.cmpweb

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.example.kmp.shared.data.repository.WasmJsSettingsRepository
import com.example.kmp.shared.di.initKoin
import com.example.kmp.shared.domain.repository.SettingsRepository
import org.koin.dsl.module

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin(
        platformModule = module {
            single<SettingsRepository> { WasmJsSettingsRepository() }
        },
    )

    CanvasBasedWindow(canvasElementId = "ComposeTarget") {
        App()
    }
}