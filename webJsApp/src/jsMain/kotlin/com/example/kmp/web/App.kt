package com.example.kmp.web

import com.example.kmp.web.ui.pages.CharacterDetailPage
import com.example.kmp.web.ui.pages.CharacterListPage
import kotlinx.browser.window
import org.w3c.dom.Element

class App(private val container: Element) {

    private var currentPage: Page? = null

    fun start() {
        window.onhashchange = { navigate() }
        navigate()
    }

    private fun navigate() {
        currentPage?.destroy()
        container.innerHTML = ""

        val hash = window.location.hash.removePrefix("#")
        currentPage = when {
            hash.startsWith("detail/") -> {
                val id = hash.removePrefix("detail/").toIntOrNull() ?: 1
                CharacterDetailPage(container, id)
            }
            else -> CharacterListPage(container)
        }

        currentPage?.render()
    }
}

interface Page {
    fun render()
    fun destroy()
}
