//
//  CharacterListViewModelWrapper.swift
//  KMPGetStartedCodelab
//
//

import SwiftUI
import sharedKit

class CharacterListViewModelWrapper: ObservableObject {
    @Published var characters: [AnimeCharacter] = []
    @Published var isLoading = true
    @Published var errorMessage: String?

    private let viewModel = KoinHelper.shared.getCharacterListViewModel()

    init() {
        observeCharacters()
        observeIsLoading()
        observeErrorMessage()
    }

    private func observeCharacters() {
        Task { @MainActor in
            for await value in viewModel.characters {
                self.characters = value
            }
        }
    }

    private func observeIsLoading() {
        Task { @MainActor in
            for await value in viewModel.isLoading {
                self.isLoading = value.boolValue
            }
        }
    }

    private func observeErrorMessage() {
        Task { @MainActor in
            for await value in viewModel.errorMessage {
                self.errorMessage = value
            }
        }
    }
}