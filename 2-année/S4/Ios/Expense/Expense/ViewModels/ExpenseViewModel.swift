//
//  ExpenseViewModel.swift
//  Expense
//
//  Created by da silveira lucas on 07/02/2024.
//

import Foundation

class ExpenseViewModel: ObservableObject {
    
    @Published var money: [Money] = []
    
    init(){
        getMoney()
    }
    
    func getMoney(){
        money.append(contentsOf: Money.testData)
    }
    
    func addItem(name: String, price: Int, typeExpense: typeExpense){
        let newMoney = Money(name: name, price: 0, typeExpense: typeExpense)
        money.append(newMoney)
    }
    
    func deleteItem(indexSet: IndexSet){
        money.remove(atOffsets: indexSet)
    }
    
    func moveItem(from: IndexSet, to: Int){
        money.move(fromOffsets: from, toOffset: to)
    }
}
