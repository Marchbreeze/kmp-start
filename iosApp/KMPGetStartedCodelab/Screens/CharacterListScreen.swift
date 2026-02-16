//
//  CharacterListScreen.swift
//  KMPGetStartedCodelab
//
//

import SwiftUI
import sharedKit

struct CharacterListScreen: View {
    @StateObject private var viewModel = CharacterListViewModelWrapper()

    var body: some View {
        NavigationStack {
            Group {
                if viewModel.isLoading {
                    ProgressView("Loading characters...")
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                } else if let error = viewModel.errorMessage {
                    VStack(spacing: 12) {
                        Image(systemName: "exclamationmark.triangle")
                            .font(.largeTitle)
                            .foregroundStyle(.red)
                        Text(error)
                            .foregroundStyle(.secondary)
                            .multilineTextAlignment(.center)
                            .padding(.horizontal)
                    }
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                } else {
                    List(viewModel.characters, id: \.id) { character in
                        NavigationLink(destination: CharacterDetailScreen(characterId: character.id)) {
                            CharacterRow(character: character)
                        }
                    }
                    .listStyle(.plain)
                }
            }
            .navigationTitle("Rick and Morty")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    NavigationLink(destination: SettingsScreen()) {
                        Image(systemName: "gearshape")
                    }
                }
                ToolbarItem(placement: .bottomBar) {
                    Text("Running on \(platform())")
                        .font(.caption)
                        .foregroundStyle(.secondary)
                }
            }
        }
    }
}

#Preview {
    CharacterListScreen()
}
