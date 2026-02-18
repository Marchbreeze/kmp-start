package com.example.kmp.web

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.kmp.shared.data.local.createDataStore
import com.example.kmp.shared.di.initKoin
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin(dataStore = createDataStore())

    val canvas = document.getElementById("ComposeTarget") as org.w3c.dom.HTMLCanvasElement
    ComposeViewport(canvas) {
        App()
    }
}