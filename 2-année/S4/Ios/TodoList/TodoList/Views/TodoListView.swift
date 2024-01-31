//
//  ContentView.swift
//  TodoList
//
//  Created by da silveira lucas on 31/01/2024.
//

import SwiftUI

struct TodoListView: View {
    
    var data: [Todo]
    
    var body: some View {
        NavigationView{
            List {
                ForEach(data){todo in
                    RowView(todo: todo)
                }
            }
            .listStyle(PlainListStyle())
            .navigationTitle("Todo")
            .toolbar{
                ToolbarItem(placement: .navigationBarLeading) {
                    EditButton()
                }
                ToolbarItem(placement: .navigationBarTrailing){
                    NavigationLink("Add", destination: AddTodoView())
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        TodoListView(data: Todo.testData)
    }
}
