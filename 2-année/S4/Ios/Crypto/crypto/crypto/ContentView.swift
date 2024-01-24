//
//  ContentView.swift
//  crypto
//
//  Created by da silveira lucas on 24/01/2024.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        
        ZStack{
            
            
            VStack {
                HStack{
                    Text("Crypto App")
                        .font(.largeTitle)
                        .bold()
                        .foregroundStyle(.green)
                }
               
                
                HStack{
                    
                    Image(systemName: "calendar")
                    Text("Monday September 13, 2023")
                }
                Spacer()
                
                HStack(spacing:18){
                    CryptoNameView(name: "BTC", imageName: "rectangle", money: "$", price: 51)
                    CryptoNameView(name: "ETH", imageName: "rectangle", money: "$", price: 21)
                    CryptoNameView(name: "XRP", imageName: "rectangle", money: "$", price: 6)
                    CryptoNameView(name: "XLM", imageName: "rectangle", money: "$", price: 11)
                }
                
                Spacer()
                
                Button{
                    print("marche")
                    
                } label: {
                    Text("Convert to euro")
                        .frame(width: 250, height: 50)
                        .background(.green)
                        .font(.title2)
                        .bold()
                        .cornerRadius(12)
                        .foregroundStyle(.white)
                }
            }
        }
        
        
        .padding()
        
        
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


struct CryptoNameView: View{
    var name : String
    var imageName: String
    var money: String
    var price: Int
    
    var body: some View{
        VStack{
            Text(name)
                .font(.title2)
                .foregroundStyle(.black)
            
            Image(systemName: imageName)
                .renderingMode(.original)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 40, height: 40)
            
            HStack{
                Text(money)
                    .font(.title2)
                    .foregroundStyle(.black)
                
                Text("\(price) k")
                    .font(.title2)
                    .foregroundStyle(.black)
                
            }
        }
    }

    
}
