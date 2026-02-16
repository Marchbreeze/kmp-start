package com.example.kmp.shared.di

import com.example.kmp.shared.presentation.viewmodel.CharacterDetailViewModel
import com.example.kmp.shared.presentation.viewmodel.CharacterListViewModel
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform

object KoinHelper {
    fun initKoin() {
        startKoin {
            modules(sharedModule)
        }
    }

    private val koin get() = KoinPlatform.getKoin()

    fun getCharacterListViewModel(): CharacterListViewModel {
        return koin.get()
    }

    fun getCharacterDetailViewModel(characterId: Int): CharacterDetailViewModel {
        return koin.get { parametersOf(characterId) }
    }
}
