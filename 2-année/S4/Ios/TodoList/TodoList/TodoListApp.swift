//
//  TodoListApp.swift
//  TodoList
//
//  Created by da silveira lucas on 31/01/2024.
//

import SwiftUI

@main
struct TodoListApp: App {
    var body: some Scene {
        WindowGroup {
            TodoListView()
                .environmentObject(TodoViewModel())
        }
    }
}
