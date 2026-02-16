//
//  KMPGetStartedCodelabApp.swift
//  KMPGetStartedCodelab
//
//

import SwiftUI
import sharedKit

@main
struct KMPGetStartedCodelabApp: App {
    init() {
        try? KoinHelper.shared.initKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
