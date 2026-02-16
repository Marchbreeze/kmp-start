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
        KoinHelperKt.initKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}