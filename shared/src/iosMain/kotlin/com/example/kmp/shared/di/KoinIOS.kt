package com.example.kmp.shared.di

import com.example.kmp.shared.presentation.viewmodel.CharacterDetailViewModel
import com.example.kmp.shared.presentation.viewmodel.CharacterListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

object KoinIOS : KoinComponent {

    fun initialize() {
        initKoin()
    }

    fun getCharacterListViewModel(): CharacterListViewModel = get()

    fun getCharacterDetailViewModel(characterId: Int): CharacterDetailViewModel =
        get { parametersOf(characterId) }
}
