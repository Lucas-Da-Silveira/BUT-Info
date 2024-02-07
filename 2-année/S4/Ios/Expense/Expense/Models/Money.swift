//
//  Money.swift
//  Expense
//
//  Created by da silveira lucas on 07/02/2024.
//

import Foundation

enum typeExpense: String{
    case perso = "Perso"
    case vacation = "Vacation"
    case pro = "Pro"
}

struct Money : Identifiable{
    var id = UUID()
    var name : String
    var price : Int
    var typeExpense: typeExpense

    
    static var testData = [
        Money(name: "Apple", price: 120, typeExpense: .perso),
        Money(name: "Airbnb", price: 1200, typeExpense: .perso),
        Money(name: "McDonald", price: 300, typeExpense: .vacation),
        Money(name: "Bakery", price: 10, typeExpense: .perso),
        Money(name: "Mechanic", price: 1000, typeExpense: .pro)
    ]
}
