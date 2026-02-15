package com.example.kmp.shared.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmp.shared.data.model.AnimeCharacter
import com.example.kmp.shared.data.remote.RickAndMortyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val characterId: Int) : ViewModel() {
    private val api = RickAndMortyApi()

    private val _character = MutableStateFlow<AnimeCharacter?>(null)
    val character: StateFlow<AnimeCharacter?> = _character.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadCharacter()
    }

    private fun loadCharacter() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val result = api.getCharacter(characterId)
                _character.value = result
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
