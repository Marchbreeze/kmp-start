package com.example.kmp.shared.di

import com.example.kmp.shared.data.remote.RickAndMortyApi
import com.example.kmp.shared.data.remote.createHttpClient
import com.example.kmp.shared.data.repository.CharacterRepositoryImpl
import com.example.kmp.shared.domain.repository.CharacterRepository
import com.example.kmp.shared.domain.usecase.GetCharacterDetailUseCase
import com.example.kmp.shared.domain.usecase.GetCharactersUseCase
import com.example.kmp.shared.presentation.viewmodel.CharacterDetailViewModel
import com.example.kmp.shared.presentation.viewmodel.CharacterListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    // Network
    single { createHttpClient() }
    single { RickAndMortyApi(get()) }

    // Repository
    singleOf(::CharacterRepositoryImpl) bind CharacterRepository::class

    // Use Cases
    factoryOf(::GetCharactersUseCase)
    factoryOf(::GetCharacterDetailUseCase)

    // ViewModels
    viewModelOf(::CharacterListViewModel)
    viewModel { params ->
        CharacterDetailViewModel(
            characterId = params.get(),
            getCharacterDetailUseCase = get(),
        )
    }
}