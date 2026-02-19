package com.example.kmp.web.ui.pages

import com.example.kmp.shared.platform
import com.example.kmp.shared.presentation.viewmodel.SettingsViewModel
import com.example.kmp.web.Page
import com.example.kmp.web.ui.components.createTopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.Element

class SettingsPage(
    private val container: Element,
) : Page, KoinComponent {

    private val scope = CoroutineScope(Dispatchers.Default + Job())
    private val viewModel: SettingsViewModel = get()

    override fun render() {
        val topBar = createTopBar(
            title = "Settings",
            showBack = true,
            onBackClick = {
                kotlinx.browser.window.location.hash = "#list"
            },
        )
        container.appendChild(topBar)

        val contentDiv = kotlinx.browser.document.createElement("div")
        contentDiv.className = "settings-container"
        contentDiv.id = "settings-content"
        container.appendChild(contentDiv)

        renderSettingsUI(contentDiv)

        scope.launch {
            viewModel.settings.collect { settings ->
                val darkToggle = kotlinx.browser.document
                    .getElementById("toggle-dark") as? HTMLInputElement
                darkToggle?.checked = settings.isDarkTheme

                val perPageSlider = kotlinx.browser.document
                    .getElementById("slider-per-page") as? HTMLInputElement
                perPageSlider?.value = settings.charactersPerPage.toString()

                val perPageLabel = kotlinx.browser.document
                    .getElementById("per-page-value")
                perPageLabel?.textContent = settings.charactersPerPage.toString()
            }
        }
    }

    private fun renderSettingsUI(container: Element) {
        container.innerHTML = """
            <div class="settings-section">
                <h3>Appearance</h3>
                <div class="setting-row">
                    <div>
                        <div class="setting-label">Dark Theme</div>
                        <div class="setting-desc">Toggle dark/light appearance</div>
                    </div>
                    <label class="toggle">
                        <input type="checkbox" id="toggle-dark">
                        <span class="slider"></span>
                    </label>
                </div>
            </div>

            <div class="settings-section">
                <h3>Data</h3>
                <div class="setting-row">
                    <div>
                        <div class="setting-label">Characters Per Page</div>
                        <div class="setting-desc">
                            Currently: <span id="per-page-value">20</span>
                        </div>
                    </div>
                    <input type="range" id="slider-per-page" min="5" max="50" step="5" value="20">
                </div>
            </div>

            <div class="settings-section">
                <h3>About</h3>
                <div class="setting-row">
                    <div>
                        <div class="setting-label">Platform</div>
                        <div class="setting-desc">${platform()}</div>
                    </div>
                </div>
                <div class="setting-row">
                    <div>
                        <div class="setting-label">Architecture</div>
                        <div class="setting-desc">KMP + Kotlin/JS + HTML/CSS (No CMP)</div>
                    </div>
                </div>
                <div class="setting-row">
                    <div>
                        <div class="setting-label">Version</div>
                        <div class="setting-desc">1.0.0</div>
                    </div>
                </div>
            </div>
        """.trimIndent()

        val darkToggle = kotlinx.browser.document
            .getElementById("toggle-dark") as? HTMLInputElement
        darkToggle?.addEventListener("change", {
            viewModel.toggleDarkTheme(darkToggle.checked)
        })

        val perPageSlider = kotlinx.browser.document
            .getElementById("slider-per-page") as? HTMLInputElement
        perPageSlider?.addEventListener("input", {
            val value = perPageSlider.value.toIntOrNull() ?: 20
            kotlinx.browser.document.getElementById("per-page-value")?.textContent =
                value.toString()
            viewModel.updateCharactersPerPage(value)
        })
    }

    override fun destroy() {
        scope.cancel()
    }
}
