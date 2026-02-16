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
        KoinIOS.shared.initialize()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
