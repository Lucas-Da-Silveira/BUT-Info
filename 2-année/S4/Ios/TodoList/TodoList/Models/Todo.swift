//
//  Todo.swift
//  TodoList
//
//  Created by da silveira lucas on 31/01/2024.
//

import Foundation

enum Priority: String{
    case low = "Low"
    case normal = "Normal"
    case high = "High"
}

struct Todo: Identifiable{
    var id = UUID()
    var title: String
    var isCompleted: Bool
    var priority: Priority
    
    static var testData = [
        Todo(title: "Task 1", isCompleted: true, priority: .low),
        Todo(title: "Task 2", isCompleted: false, priority: .high),
        Todo(title: "Task 3", isCompleted: true, priority: .normal),
        Todo(title: "Task 4", isCompleted: false, priority: .low),
    ]
}
