//
//  Todo.swift
//  TodoList
//
//  Created by da silveira lucas on 31/01/2024.
//

import Foundation

struct Todo: Identifiable{
    var id = UUID()
    var title: String
    var isCompleted: Bool
    
    static var testData = [
        Todo(title: "Task 1", isCompleted: true),
        Todo(title: "Task 2", isCompleted: false),
        Todo(title: "Task 3", isCompleted: true),
        Todo(title: "Task 4", isCompleted: false),
    ]
}
