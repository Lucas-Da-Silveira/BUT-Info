//
//  AddExpsenseView.swift
//  Expense
//
//  Created by da silveira lucas on 07/02/2024.
//

import SwiftUI

struct AddExpsenseView: View {
    @State var ExpenseTitle: String = ""
    @State var ExpensePrice: Int = 0
    
    var body: some View {
        
        VStack{
            TextField("Enter the destination expense", text: $ExpenseTitle)
                .padding()
                .frame(height: 55)
                .background(Color(.systemGray4))
                .cornerRadius(10)
            
            TextField("",value: $ExpensePrice, format: .number)
                .padding()
                .frame(height: 55)
                .background(Color(.systemGray4))
                .cornerRadius(10)
            Spacer()
            Button{
                
            } label: {
                Text("Save")
                    .font(.headline)
                    .frame(height: 55)
                    .frame(maxWidth: .infinity)
                    .background(.blue)
                    .foregroundStyle(.white)
                    .cornerRadius(10)
            }
            
        }
        .padding(14)
        .navigationTitle("Add a Expense")
    }
}

struct AddExpsenseView_Previews: PreviewProvider {
    static var previews: some View {
        AddExpsenseView()
    }
}
