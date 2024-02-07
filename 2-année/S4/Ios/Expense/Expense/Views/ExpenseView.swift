//
//  ContentView.swift
//  Expense
//
//  Created by da silveira lucas on 07/02/2024.
//

import SwiftUI

struct ExpenseView: View {
    
    @EnvironmentObject var expenseViewModel: ExpenseViewModel
    
    var body: some View {
        NavigationView{
            List {
                ForEach(expenseViewModel.money){money in
                    RowView(money: money)
                }
                .onDelete(perform: expenseViewModel.deleteItem)
                .onMove(perform: expenseViewModel.moveItem)
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
        ExpenseView()
            .environmentObject(ExpenseViewModel())
    }
}
