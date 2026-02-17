package com.example.kmp.shared.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmp.shared.domain.model.AppSettings
import com.example.kmp.shared.domain.usecase.GetSettingsUseCase
import com.example.kmp.shared.domain.usecase.UpdateSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val updateSettingsUseCase: UpdateSettingsUseCase,
) : ViewModel() {

    private val _settings = MutableStateFlow(AppSettings())
    val settings: StateFlow<AppSettings> = _settings.asStateFlow()

    init {
        observeSettings()
    }

    private fun observeSettings() {
        viewModelScope.launch {
            getSettingsUseCase().collect { appSettings ->
                _settings.value = appSettings
            }
        }
    }

    fun toggleDarkTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            updateSettingsUseCase.updateDarkTheme(isDarkTheme)
        }
    }

    fun toggleUseSystemTheme(useSystemTheme: Boolean) {
        viewModelScope.launch {
            updateSettingsUseCase.updateUseSystemTheme(useSystemTheme)
        }
    }

    fun updateCharactersPerPage(count: Int) {
        viewModelScope.launch {
            updateSettingsUseCase.updateCharactersPerPage(count)
        }
    }
}
