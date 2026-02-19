package com.example.kmp.web.ui.components

import com.example.kmp.shared.data.model.AnimeCharacter
import kotlinx.browser.document
import org.w3c.dom.Element

fun createCharacterCard(
    character: AnimeCharacter,
    onClick: () -> Unit,
): Element {
    val card = document.createElement("div")
    card.className = "character-card"
    card.addEventListener("click", { onClick() })

    val statusClass = when (character.status.lowercase()) {
        "alive" -> "alive"
        "dead" -> "dead"
        else -> "unknown"
    }

    card.innerHTML = """
        <img src="${character.image}" alt="${character.name}" loading="lazy">
        <div class="card-body">
            <h3>${character.name}</h3>
            <div class="status">
                <span class="status-dot $statusClass"></span>
                ${character.status} - ${character.species}
            </div>
        </div>
    """.trimIndent()

    return card
}
