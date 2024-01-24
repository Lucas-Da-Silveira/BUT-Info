//
//  ContentView.swift
//  Weather
//
//  Created by da silveira lucas on 17/01/2024.
//

import SwiftUI

struct ContentView: View {
    
    @State var isNight = false
    
    @EnvironmentObject var forecastViewModel : ForecastsViewModel
    
    var body: some View {
        
        ZStack{
            LinearGradient(gradient: Gradient(colors: [isNight ? Color.black : Color.blue,
                Color.white]),startPoint: .topLeading, endPoint: .bottomTrailing)
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                
                HStack{
                    Text("Porto")
                        .font(.largeTitle)
                        .bold()
                        .foregroundStyle(.white)
                        
                        
                    Button(){
                        forecastViewModel.reloadTemperatures()
                        
                    }label: {
                        Image(systemName: "arrow.clockwise.circle")
                            .font(.title)
                            .foregroundStyle(.white)
                    }
                }
                .padding([.top, .trailing])
                
                Image(systemName: isNight ? "moon.stars.fill" : "cloud.sun.fill")
                    .renderingMode(.original)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 180, height: 180)
                
                Text("24°")
                    .font(.system(size: 70, weight: .medium))
                    .foregroundStyle(.white)
                
                HStack(spacing: 18){
                    WeatherDayView(dayOfWeek: forecastViewModel.getDay(for: 0), imageName: "cloud.sun.fill", temperature: forecastViewModel.getTemp(for: 0))
                    WeatherDayView(dayOfWeek: forecastViewModel.getDay(for: 1), imageName: "sun.max.fill", temperature: forecastViewModel.getTemp(for: 1))
                    WeatherDayView(dayOfWeek: forecastViewModel.getDay(for: 2), imageName: "wind.snow", temperature: forecastViewModel.getTemp(for: 2))
                    WeatherDayView(dayOfWeek: forecastViewModel.getDay(for: 3), imageName: "snow", temperature: forecastViewModel.getTemp(for: 3))
                    WeatherDayView(dayOfWeek: forecastViewModel.getDay(for: 4), imageName: "cloud.fill", temperature: forecastViewModel.getTemp(for: 4))
                }
                .padding()
                
                Spacer()
                
                Button{
                    print("Button Tapped")
                    isNight.toggle()
                    
                } label: {
                    Text("Change Day Time")
                        .frame(width: 250, height: 50)
                        .background(.white)
                        .font(.title2)
                        .bold()
                        .cornerRadius(12)
                }
                
                Spacer()
            }
        }
    }
    
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(ForecastsViewModel())
    }
}


struct WeatherDayView: View{
    
    var dayOfWeek: String
    var imageName: String
    var temperature: Int
    
    var body: some View{
        VStack{
            Text(dayOfWeek)
                .font(.title2)
                .foregroundStyle(.white)
            Image(systemName: imageName)
                .renderingMode(.original)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 40, height: 40)
            Text("\(temperature)°")
                .font(.title2)
                .foregroundStyle(.white)
        }
        
    }
}
