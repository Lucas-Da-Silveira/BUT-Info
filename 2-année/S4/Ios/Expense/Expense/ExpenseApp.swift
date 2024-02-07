//
//  ExpenseApp.swift
//  Expense
//
//  Created by da silveira lucas on 07/02/2024.
//

import SwiftUI

@main
struct ExpenseApp: App {
    var body: some Scene {
        WindowGroup {
            ExpenseView(data: Money.testData)
        }
    }
}
