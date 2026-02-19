package com.example.kmp.web.ui.pages

import com.example.kmp.shared.data.model.AnimeCharacter
import com.example.kmp.shared.presentation.viewmodel.CharacterDetailViewModel
import com.example.kmp.web.Page
import com.example.kmp.web.ui.components.createTopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.w3c.dom.Element

class CharacterDetailPage(
    private val container: Element,
    private val characterId: Int,
) : Page, KoinComponent {

    private val scope = CoroutineScope(Dispatchers.Default + Job())
    private val viewModel: CharacterDetailViewModel = get { parametersOf(characterId) }

    override fun render() {
        val topBar = createTopBar(
            title = "Character Detail",
            showBack = true,
            onBackClick = {
                kotlinx.browser.window.location.hash = "#list"
            },
        )
        container.appendChild(topBar)

        val contentDiv = kotlinx.browser.document.createElement("div")
        contentDiv.id = "detail-content"
        container.appendChild(contentDiv)

        scope.launch {
            viewModel.isLoading.collect { isLoading ->
                if (isLoading) {
                    contentDiv.innerHTML = """
                        <div class="loading">
                            <div class="spinner"></div>
                            Loading character...
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
                            <button onclick="window.location.hash='#list'">Go Back</button>
                        </div>
                    """.trimIndent()
                }
            }
        }

        scope.launch {
            viewModel.character.collect { character ->
                if (character != null) {
                    contentDiv.innerHTML = ""
                    contentDiv.appendChild(buildDetailView(character))
                }
            }
        }
    }

    private fun buildDetailView(character: AnimeCharacter): Element {
        val detail = kotlinx.browser.document.createElement("div")
        detail.className = "detail-container"

        val statusClass = when (character.status.lowercase()) {
            "alive" -> "alive"
            "dead" -> "dead"
            else -> "unknown"
        }

        val episodeCount = character.episode.size

        detail.innerHTML = """
            <div class="detail-hero">
                <img src="${character.image}" alt="${character.name}">
                <h2>${character.name}</h2>
                <div class="status">
                    <span class="status-dot $statusClass"></span>
                    ${character.status} - ${character.species}
                </div>
            </div>
            <div class="info-grid">
                <div class="info-card">
                    <div class="label">Gender</div>
                    <div class="value">${character.gender}</div>
                </div>
                <div class="info-card">
                    <div class="label">Origin</div>
                    <div class="value">${character.origin.name}</div>
                </div>
                <div class="info-card">
                    <div class="label">Location</div>
                    <div class="value">${character.location.name}</div>
                </div>
                <div class="info-card">
                    <div class="label">Episodes</div>
                    <div class="value">$episodeCount episode(s)</div>
                </div>
            </div>
        """.trimIndent()

        return detail
    }

    override fun destroy() {
        scope.cancel()
    }
}
