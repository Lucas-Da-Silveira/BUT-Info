//
//  RowView.swift
//  Expense
//
//  Created by da silveira lucas on 07/02/2024.
//

import SwiftUI

struct RowView: View {
    
    let money : Money
    
    var body: some View {
        HStack{
            Text(money.name)
            
            
            Spacer()
        }
    }
}

struct RowView_Previews: PreviewProvider {
    static var previews: some View {
        RowView(money: Money.testData[0])
    }
}
