package com.example.kmp.web

import com.example.kmp.shared.data.repository.JsSettingsRepository
import com.example.kmp.shared.di.initKoin
import com.example.kmp.shared.domain.repository.SettingsRepository
import kotlinx.browser.document
import org.koin.dsl.module

fun main() {
    initKoin(
        platformModule = module {
            single<SettingsRepository> { JsSettingsRepository() }
        },
    )

    val appContainer = document.getElementById("app")
        ?: error("Could not find #app element")

    val app = App(appContainer)
    app.start()
}
