//
//  ContentView.swift
//  Expense
//
//  Created by da silveira lucas on 07/02/2024.
//

import SwiftUI

struct ExpenseView: View {
    
    var data: [Money]
    
    var body: some View {
        NavigationView{
            List {
                ForEach(data){money in
                    RowView(money: money)
                }
            }
            .listStyle(PlainListStyle())
            .navigationTitle("Expense")
            .toolbar{
                ToolbarItem(placement: .navigationBarLeading) {
                    EditButton()
                }
                ToolbarItem(placement: .navigationBarTrailing){
                    NavigationLink("Add", destination: AddExpsenseView())
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ExpenseView(data: Money.testData)
    }
}
