package com.example.kmp.web.ui.components

import kotlinx.browser.document
import org.w3c.dom.Element

fun createTopBar(
    title: String,
    subtitle: String? = null,
    showBack: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    onSettingsClick: (() -> Unit)? = null,
): Element {
    val topBar = document.createElement("div")
    topBar.className = "top-bar"

    if (showBack && onBackClick != null) {
        val backBtn = document.createElement("button")
        backBtn.className = "back-btn"
        backBtn.textContent = "\u2190"
        backBtn.addEventListener("click", { onBackClick() })
        topBar.appendChild(backBtn)
    }

    val titleContainer = document.createElement("div")
    titleContainer.setAttribute("style", "flex: 1;")

    val titleEl = document.createElement("h1")
    titleEl.textContent = title
    titleContainer.appendChild(titleEl)

    if (subtitle != null) {
        val subtitleEl = document.createElement("span")
        subtitleEl.className = "subtitle"
        subtitleEl.textContent = subtitle
        titleContainer.appendChild(subtitleEl)
    }

    topBar.appendChild(titleContainer)

    if (onSettingsClick != null) {
        val settingsBtn = document.createElement("button")
        settingsBtn.textContent = "\u2699"
        settingsBtn.addEventListener("click", { onSettingsClick() })
        topBar.appendChild(settingsBtn)
    }

    return topBar
}
