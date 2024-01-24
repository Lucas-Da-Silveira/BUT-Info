//
//  ForecastsViewModel.swift
//  weather1
//
//  Created by da silveira lucas on 24/01/2024.
//

import Foundation

class ForecastsViewModel: ObservableObject{
    
    @Published var forecasts: [Temperature] = []
    var i=0
    
    init(){
        getForecasts()
    }
    
    func getForecasts() {
        
        let newForecasts = [
            Temperature(dayOfWeek: "MON", Temp: 20),
            Temperature(dayOfWeek: "TUE", Temp: 28),
            Temperature(dayOfWeek: "WED", Temp: 0),
            Temperature(dayOfWeek: "THU", Temp: -4),
            Temperature(dayOfWeek: "FRI", Temp: 7),
        ]
        
        forecasts = newForecasts
        
        func getDay(for index: Int) -> String{
            return forecasts[index].dayOfWeek
        }
        
        func getTemp(for index: Int) -> Int{
            return forecasts[index].Temp
        }
        
        func reloadTemperatures(){
            for i in 0..<forecasts.count{
                forecasts[i].Temp += 1
            }
        }
    }
}
