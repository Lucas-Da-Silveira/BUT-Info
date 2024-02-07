//
//  Money.swift
//  Expense
//
//  Created by da silveira lucas on 07/02/2024.
//

import Foundation

struct Money : Identifiable{
    var id = UUID()
    var name : String
    var price : Int
    var isPerso: Bool
    var isVac: Bool
    var isPro: Bool
    
    static var testData = [
        Money(name: "Apple", price: 120, isPerso: true, isVac: false, isPro: false),
        Money(name: "Airbnb", price: 1200, isPerso: true, isVac: false, isPro: false),
        Money(name: "McDonald", price: 300, isPerso: false, isVac: true, isPro: false),
        Money(name: "Bakery", price: 10, isPerso: true, isVac: false, isPro: false),
        Money(name: "Mechanic", price: 1000, isPerso: false, isVac: false, isPro: true)
    ]
}
