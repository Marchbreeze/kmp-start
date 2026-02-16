//
//  CharacterDetailViewModelWrapper.swift
//  KMPGetStartedCodelab
//
//

import SwiftUI
import sharedKit

class CharacterDetailViewModelWrapper: ObservableObject {
    @Published var character: AnimeCharacter?
    @Published var isLoading = true
    @Published var errorMessage: String?

    private let viewModel: CharacterDetailViewModel

    init(characterId: Int32) {
        self.viewModel = KoinHelper.shared.getCharacterDetailViewModel(characterId: characterId)
        observeCharacter()
        observeIsLoading()
        observeErrorMessage()
    }

    private func observeCharacter() {
        Task { @MainActor in
            for await value in viewModel.character {
                self.character = value
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