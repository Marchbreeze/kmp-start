package com.example.kmp.web.ui.pages

import com.example.kmp.shared.platform
import com.example.kmp.shared.presentation.viewmodel.CharacterListViewModel
import com.example.kmp.web.Page
import com.example.kmp.web.ui.components.createCharacterCard
import com.example.kmp.web.ui.components.createTopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.html.div
import kotlinx.html.dom.append
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.w3c.dom.Element

class CharacterListPage(
    private val container: Element,
) : Page, KoinComponent {

    private val scope = CoroutineScope(Dispatchers.Default + Job())
    private val viewModel: CharacterListViewModel = get()

    override fun render() {
        container.append {
            div {
                // placeholder for top bar
            }
        }

        val topBar = createTopBar(
            title = "Rick and Morty",
            subtitle = "Running on ${platform()}",
        )
        container.insertBefore(topBar, container.firstChild)

        val contentDiv = kotlinx.browser.document.createElement("div")
        contentDiv.id = "list-content"
        container.appendChild(contentDiv)

        scope.launch {
            viewModel.isLoading.collect { isLoading ->
                if (isLoading) {
                    contentDiv.innerHTML = """
                        <div class="loading">
                            <div class="spinner"></div>
                            Loading characters...
                        </div>
                    """.trimIndent()
                }
            }
        }

        scope.launch {
            viewModel.errorMessage.collect { error ->
                if (error != null) {
                    contentDiv.innerHTML = """
                        <div class="error-message">
                            <p>$error</p>
                            <button onclick="window.location.reload()">Retry</button>
                        </div>
                    """.trimIndent()
                }
            }
        }

        scope.launch {
            viewModel.characters.collect { characters ->
                if (characters.isNotEmpty()) {
                    contentDiv.innerHTML = ""
                    val grid = kotlinx.browser.document.createElement("div")
                    grid.className = "character-grid"

                    characters.forEach { character ->
                        val card = createCharacterCard(character) {
                            kotlinx.browser.window.location.hash = "#detail/${character.id}"
                        }
                        grid.appendChild(card)
                    }

                    contentDiv.appendChild(grid)
                }
            }
        }
    }

    override fun destroy() {
        scope.cancel()
    }
}
