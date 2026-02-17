import SwiftUI
import sharedKit

class SettingsViewModelWrapper: ObservableObject {
    @Published var isDarkTheme = false
    @Published var useSystemTheme = true
    @Published var charactersPerPage: Int = 20

    private let viewModel = KoinIOS.shared.getSettingsViewModel()

    init() {
        observeSettings()
    }

    private func observeSettings() {
        Task { @MainActor in
            for await value in viewModel.settings {
                self.isDarkTheme = value.isDarkTheme
                self.useSystemTheme = value.useSystemTheme
                self.charactersPerPage = Int(value.charactersPerPage)
            }
        }
    }

    func toggleDarkTheme(_ isDark: Bool) {
        viewModel.toggleDarkTheme(isDarkTheme: isDark)
    }

    func toggleUseSystemTheme(_ useSystem: Bool) {
        viewModel.toggleUseSystemTheme(useSystemTheme: useSystem)
    }

    func updateCharactersPerPage(_ count: Int) {
        viewModel.updateCharactersPerPage(count: Int32(count))
    }
}
