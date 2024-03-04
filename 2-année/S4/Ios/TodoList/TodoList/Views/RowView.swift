//
//  RowView.swift
//  TodoList
//
//  Created by da silveira lucas on 31/01/2024.
//

import SwiftUI

struct RowView: View {
    
    let todo: Todo
    
    var body: some View {
        
        HStack{
            Image(systemName: todo.isCompleted ? "checkmark.circle" : "circle")
                .foregroundColor(todo.isCompleted ? .green : .red)
            
            Text(todo.title)
            
            Spacer()
            
            Text(todo.priority.rawValue)
                .font(.footnote)
                .padding(3)
                .foregroundStyle(Color(.systemGray2))
                .frame(minWidth: 62)
                .overlay(
                    Capsule().stroke(Color(.systemGray2), lineWidth: 0.75)
                )
        
        .font(.title2)
        .padding(.vertical, 8)
    }
}

struct RowView_Previews: PreviewProvider {
    static var previews: some View {
        RowView(todo: Todo.testData[0])
            .previewLayout(.sizeThatFits)
        
        RowView(todo: Todo.testData[2])
            .previewLayout(.sizeThatFits)
    }
        
}
