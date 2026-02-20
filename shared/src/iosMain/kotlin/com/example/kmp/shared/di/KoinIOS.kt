package com.example.kmp.shared.di

import com.example.kmp.shared.data.local.createDataStore
import com.example.kmp.shared.data.repository.DataStoreSettingsRepository
import com.example.kmp.shared.domain.repository.SettingsRepository
import com.example.kmp.shared.presentation.viewmodel.CharacterDetailViewModel
import com.example.kmp.shared.presentation.viewmodel.CharacterListViewModel
import com.example.kmp.shared.presentation.viewmodel.SettingsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

object KoinIOS : KoinComponent {

    fun initialize() {
        val dataStore = createDataStore()
        initKoin(
            platformModule = module {
                single { dataStore }
                single<SettingsRepository> { DataStoreSettingsRepository(get()) }
            },
        )
    }

    fun getCharacterListViewModel(): CharacterListViewModel = get()

    fun getCharacterDetailViewModel(characterId: Int): CharacterDetailViewModel =
        get { parametersOf(characterId) }

    fun getSettingsViewModel(): SettingsViewModel = get()
}
