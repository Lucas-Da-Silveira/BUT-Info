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
        }
        .font(.title2)
        .padding(.vertical, 8)
    }
}

struct RowView_Previews: PreviewProvider {
    static var previews: some View {
        RowView(todo: Todo.testData[0])
            .previewLayout(.sizeThatFits)
        
        RowView(todo: Todo.testData[1])
            .previewLayout(.sizeThatFits)
    }
        
}
