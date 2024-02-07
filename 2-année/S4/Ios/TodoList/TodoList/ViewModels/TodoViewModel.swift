//
//  TodoViewModel.swift
//  TodoList
//
//  Created by da silveira lucas on 07/02/2024.
//

import Foundation

class TodoViewModel: ObservableObject {
    
    @Published var todos: [Todo] = []
    
    init(){
        getTodos()
    }
    
    func getTodos(){
        todos.append(contentsOf: Todo.testData)
    }
    
    func addItem(title: String, priority: Priority){
        let newTodo = Todo(title: title, isCompleted: false, priority: priority)
        todos.append(newTodo)
    }
    
    func deleteItem(indexSet: IndexSet){
        todos.remove(atOffsets: indexSet)
    }
    
    func moveItem(from: IndexSet, to: Int){
        todos.move(fromOffsets: from, toOffset: to)
    }
}
