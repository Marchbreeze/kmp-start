//
//  ContentView.swift
//  KMPGetStartedCodelab
//
//

import SwiftUI
import sharedKit

class CharacterListViewModelWrapper: ObservableObject {
    @Published var characters: [AnimeCharacter] = []
    @Published var isLoading = true
    @Published var errorMessage: String?

    private let viewModel = sharedKit.CharacterListViewModel()

    init() {
        observeCharacters()
        observeIsLoading()
        observeErrorMessage()
    }

    private func observeCharacters() {
        Task {
            do {
                let stream = asyncStream(for: viewModel.characters)
                for try await value in stream {
                    DispatchQueue.main.async { [weak self] in
                        self?.characters = value
                    }
                }
            } catch {
                print("Error observing characters: \(error)")
            }
        }
    }

    private func observeIsLoading() {
        Task {
            do {
                let stream = asyncStream(for: viewModel.isLoading)
                for try await value in stream {
                    DispatchQueue.main.async { [weak self] in
                        self?.isLoading = value
                    }
                }
            } catch {
                print("Error observing isLoading: \(error)")
            }
        }
    }

    private func observeErrorMessage() {
        Task {
            do {
                let stream = asyncStream(for: viewModel.errorMessage)
                for try await value in stream {
                    DispatchQueue.main.async { [weak self] in
                        self?.errorMessage = value
                    }
                }
            } catch {
                print("Error observing errorMessage: \(error)")
            }
        }
    }
}

struct ContentView: View {
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
                        CharacterRow(character: character)
                    }
                    .listStyle(.plain)
                }
            }
            .navigationTitle("Rick and Morty")
            .toolbar {
                ToolbarItem(placement: .bottomBar) {
                    Text("Running on \(platform())")
                        .font(.caption)
                        .foregroundStyle(.secondary)
                }
            }
        }
    }
}

struct CharacterRow: View {
    let character: AnimeCharacter

    var body: some View {
        HStack(spacing: 12) {
            AsyncImage(url: URL(string: character.image)) { phase in
                switch phase {
                case .success(let image):
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                case .failure:
                    Image(systemName: "person.fill")
                        .font(.title)
                        .foregroundStyle(.gray)
                default:
                    ProgressView()
                }
            }
            .frame(width: 70, height: 70)
            .clipShape(RoundedRectangle(cornerRadius: 8))

            VStack(alignment: .leading, spacing: 4) {
                Text(character.name)
                    .font(.headline)
                    .lineLimit(1)

                HStack(spacing: 6) {
                    Circle()
                        .fill(statusColor(for: character.status))
                        .frame(width: 8, height: 8)
                    Text("\(character.status) - \(character.species)")
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                }

                VStack(alignment: .leading, spacing: 1) {
                    Text("Last known location:")
                        .font(.caption2)
                        .foregroundStyle(.secondary)
                    Text(character.location.name)
                        .font(.caption)
                        .fontWeight(.medium)
                        .lineLimit(1)
                }
            }
        }
        .padding(.vertical, 4)
    }

    private func statusColor(for status: String) -> Color {
        switch status.lowercased() {
        case "alive": return .green
        case "dead": return .red
        default: return .gray
        }
    }
}

#Preview {
    ContentView()
}
