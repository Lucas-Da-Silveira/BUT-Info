//
//  AddTodoView.swift
//  TodoList
//
//  Created by da silveira lucas on 31/01/2024.
//

import SwiftUI

struct AddTodoView: View {
    
    @State var todoTitle: String = ""
    @State var priority: Priority = .normal
    
    @EnvironmentObject var todoViewModel: TodoViewModel
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        
        VStack{
            TextField("Enter your task", text: $todoTitle)
                .padding()
                .frame(height: 55)
                .background(Color(.systemGray4))
                .cornerRadius(10)
            
            Picker("Priority", selection: $priority){
                ForEach(Priority.allCases, id: \.self){
                    priority in
                    Text(priority.rawValue)
                }
            }
            .pickerStyle(SegmentedPickerStyle())
            
            Button{
                todoViewModel.addItem(title: todoTitle, priority: priority)
                presentationMode.wrappedValue.dismiss()
            } label: {
                Text("Save")
                    .font(.headline)
                    .frame(height: 55)
                    .frame(maxWidth: .infinity)
                    .background(.blue)
                    .foregroundStyle(.white)
                    .cornerRadius(10)
            }
            Spacer()
        }
        .padding(14)
        .navigationTitle("Add a Todo")
    }
}

struct AddTodoView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView{
            AddTodoView()
        }
    }
}
