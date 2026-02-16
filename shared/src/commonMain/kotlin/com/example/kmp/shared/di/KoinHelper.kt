package com.example.kmp.shared.di

import com.example.kmp.shared.presentation.viewmodel.CharacterDetailViewModel
import com.example.kmp.shared.presentation.viewmodel.CharacterListViewModel
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}

object KoinHelper {
    private val koin get() = org.koin.core.context.GlobalContext.get()

    fun getCharacterListViewModel(): CharacterListViewModel {
        return koin.get()
    }

    fun getCharacterDetailViewModel(characterId: Int): CharacterDetailViewModel {
        return koin.get { parametersOf(characterId) }
    }
}