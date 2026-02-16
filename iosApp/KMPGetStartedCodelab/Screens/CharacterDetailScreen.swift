//
//  CharacterDetailScreen.swift
//  KMPGetStartedCodelab
//
//

import SwiftUI
import sharedKit

struct CharacterDetailScreen: View {
    @StateObject private var viewModel: CharacterDetailViewModelWrapper

    init(characterId: Int32) {
        _viewModel = StateObject(wrappedValue: CharacterDetailViewModelWrapper(characterId: characterId))
    }

    var body: some View {
        Group {
            if viewModel.isLoading {
                ProgressView("Loading character...")
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
            } else if let character = viewModel.character {
                CharacterDetailContent(character: character)
            }
        }
        .navigationTitle(viewModel.character?.name ?? "Character Detail")
        .navigationBarTitleDisplayMode(.inline)
    }
}

private struct CharacterDetailContent: View {
    let character: AnimeCharacter

    var body: some View {
        ScrollView {
            VStack(spacing: 0) {
                // Character Image
                AsyncImage(url: URL(string: character.image)) { phase in
                    switch phase {
                    case .success(let image):
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                    case .failure:
                        Image(systemName: "person.fill")
                            .font(.system(size: 80))
                            .foregroundStyle(.gray)
                            .frame(maxWidth: .infinity)
                    default:
                        ProgressView()
                            .frame(maxWidth: .infinity)
                    }
                }
                .frame(height: 300)
                .clipped()

                VStack(alignment: .leading, spacing: 16) {
                    // Name and Status
                    Text(character.name)
                        .font(.title)
                        .fontWeight(.bold)

                    HStack(spacing: 8) {
                        Circle()
                            .fill(statusColor(for: character.status))
                            .frame(width: 12, height: 12)
                        Text("\(character.status) - \(character.species)")
                            .font(.title3)
                            .foregroundStyle(.secondary)
                    }

                    Divider()

                    // Info Cards
                    InfoCardView(label: "Gender", value: character.gender)
                    InfoCardView(label: "Origin", value: character.origin.name)
                    InfoCardView(label: "Last Known Location", value: character.location.name)
                    InfoCardView(label: "Episodes", value: "\(character.episode.count) episode(s)")

                    if !character.type.isEmpty {
                        InfoCardView(label: "Type", value: character.type)
                    }
                }
                .padding(16)
            }
        }
    }

    private func statusColor(for status: String) -> Color {
        switch status.lowercased() {
        case "alive": return .green
        case "dead": return .red
        default: return .gray
        }
    }
}

private struct InfoCardView: View {
    let label: String
    let value: String

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(label)
                .font(.caption)
                .foregroundStyle(.secondary)
            Text(value)
                .font(.body)
                .fontWeight(.medium)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(16)
        .background(Color(.systemGray6))
        .clipShape(RoundedRectangle(cornerRadius: 12))
    }
}
