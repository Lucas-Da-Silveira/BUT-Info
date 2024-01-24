//
//  weather1App.swift
//  weather1
//
//  Created by da silveira lucas on 24/01/2024.
//

import SwiftUI

@main
struct weather1App: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(ForecastsViewModel())
        }
    }
}
