//
//  ForecastsViewModel.swift
//  crypto
//
//  Created by da silveira lucas on 31/01/2024.
//

import Foundation


class ForecastsViewModel: ObservableObject{
    
    @Published var forecasts: [Price] = []
    var i=0
    
    init(){
        getForecasts()
    }
    
    func getForecasts() {
        
        let newForecasts = [
            Price(Crypto:"BTC", Prix: 51),
            Price(Crypto:"ETH", Prix: 21),
            Price(Crypto:"XRP", Prix: 6),
            Price(Crypto:"XLM", Prix: 11),
        ]
        forecasts = newForecasts
    }
        
        
    func getCrypto(for index: Int) -> String{
        return forecasts[index].Crypto
    }
    
    func getPrice(for index: Int) -> Int{
        return forecasts[index].Prix
    }
    
    func reloadPrice(){
        for i in 0..<forecasts.count{
            forecasts[i].Prix += 1
        }
    }
}
