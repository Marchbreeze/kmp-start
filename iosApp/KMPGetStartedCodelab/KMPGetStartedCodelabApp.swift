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
        KoinHelper.shared.initKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
