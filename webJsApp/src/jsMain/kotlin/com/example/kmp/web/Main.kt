package com.example.kmp.web

import com.example.kmp.shared.data.local.createDataStore
import com.example.kmp.shared.di.initKoin
import kotlinx.browser.document

fun main() {
    initKoin(dataStore = createDataStore())

    val appContainer = document.getElementById("app")
        ?: error("Could not find #app element")

    val app = App(appContainer)
    app.start()
}
