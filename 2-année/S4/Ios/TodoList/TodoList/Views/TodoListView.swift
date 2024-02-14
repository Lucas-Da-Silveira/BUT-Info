//
//  ContentView.swift
//  TodoList
//
//  Created by da silveira lucas on 31/01/2024.
//

import SwiftUI

struct TodoListView: View {
    
    @EnvironmentObject var todoViewModel: TodoViewModel
    
    var body: some View {
        NavigationView{
            List {
                ForEach(todoViewModel.todos){todo in
                    RowView(todo: todo)
                        .onTapGesture {
                            todoViewModel.updateItem(todo:todo)
                        }
                }
                .onDelete(perform: todoViewModel.deleteItem)
                .onMove(perform: todoViewModel.moveItem)
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
        TodoListView()
            .environmentObject(TodoViewModel())
    }
}
