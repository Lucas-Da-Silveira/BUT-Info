//
//  ContentView.swift
//  crypto
//
//  Created by da silveira lucas on 24/01/2024.
//

import SwiftUI

struct ContentView: View {
    
    @State var isEuro = false
    
    @EnvironmentObject var forecastViewModel : ForecastsViewModel

    
    var body: some View {
        
        ZStack{
            Color.black
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                HStack{
                    Text("Crypto App")
                        .font(.largeTitle)
                        .bold()
                        .foregroundStyle(.green)
                    
                    Button(){
                        print("reload")
                        forecastViewModel.reloadPrice()
                        
                    }label: {
                        Image(systemName: "arrow.clockwise.circle")
                            .font(.title)
                            .foregroundStyle(.green)
                    }
                }
               
                
                HStack{
                    
                    Image(systemName: "calendar")
                        .foregroundStyle(.white)
                    Text("Monday September 13, 2023")
                        .foregroundStyle(.white)
                }
                Spacer()
                
                HStack(spacing:18){
                    CryptoNameView(name: forecastViewModel.getCrypto(for: 0), imageName: "btc", money: isEuro ?"$" : "€", price: forecastViewModel.getPrice(for: 0))
                    CryptoNameView(name: forecastViewModel.getCrypto(for: 1), imageName: "eth", money: isEuro ?"$" : "€", price: forecastViewModel.getPrice(for: 1))
                    CryptoNameView(name: forecastViewModel.getCrypto(for: 2), imageName: "xrp", money: isEuro ?"$" : "€", price: forecastViewModel.getPrice(for: 2))
                    CryptoNameView(name: forecastViewModel.getCrypto(for: 3), imageName: "xlm", money: isEuro ?"$" : "€", price: forecastViewModel.getPrice(for: 3))
                }
                
                Spacer()
                
                Button{
                    print("marche")
                    isEuro.toggle()
                    
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
            .environmentObject(ForecastsViewModel())

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
                .foregroundStyle(.white)
            
            Image(imageName)
                .renderingMode(.original)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 40, height: 40)
            
            HStack{
                Text(money)
                    .font(.title2)
                    .foregroundStyle(.white)
                
                Text("\(price) k")
                    .font(.title2)
                    .foregroundStyle(.white)
                
            }
        }
    }

    
}
