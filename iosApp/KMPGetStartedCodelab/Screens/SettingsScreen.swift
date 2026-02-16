import SwiftUI

struct SettingsScreen: View {
    @StateObject private var viewModel = SettingsViewModelWrapper()

    var body: some View {
        Form {
            Section("Theme") {
                Toggle("Use System Theme", isOn: Binding(
                    get: { viewModel.useSystemTheme },
                    set: { viewModel.toggleUseSystemTheme($0) }
                ))

                Toggle("Dark Theme", isOn: Binding(
                    get: { viewModel.isDarkTheme },
                    set: { viewModel.toggleDarkTheme($0) }
                ))
                .disabled(viewModel.useSystemTheme)
            }

            Section("Display") {
                VStack(alignment: .leading, spacing: 8) {
                    HStack {
                        Text("Characters Per Page")
                        Spacer()
                        Text("\(viewModel.charactersPerPage)")
                            .foregroundStyle(.secondary)
                            .fontWeight(.semibold)
                    }

                    Slider(
                        value: Binding(
                            get: { Double(viewModel.charactersPerPage) },
                            set: { viewModel.updateCharactersPerPage(Int($0)) }
                        ),
                        in: 5...50,
                        step: 5
                    )

                    HStack {
                        Text("5")
                            .font(.caption2)
                            .foregroundStyle(.secondary)
                        Spacer()
                        Text("50")
                            .font(.caption2)
                            .foregroundStyle(.secondary)
                    }
                }
            }

            Section("About") {
                HStack {
                    Text("App Version")
                    Spacer()
                    Text("1.0.0")
                        .foregroundStyle(.secondary)
                }

                HStack {
                    Text("Data Source")
                    Spacer()
                    Text("Rick and Morty API")
                        .foregroundStyle(.secondary)
                }

                HStack {
                    Text("Storage")
                    Spacer()
                    Text("DataStore Preferences")
                        .foregroundStyle(.secondary)
                }
            }
        }
        .navigationTitle("Settings")
        .navigationBarTitleDisplayMode(.inline)
    }
}
