//
//  ContentView.swift
//  Weather
//
//  Created by da silveira lucas on 17/01/2024.
//

import SwiftUI

struct ContentView: View {
    
    @State var isNight = false
    
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
                    WeatherDayView(dayOfWeek: "MON", imageName: "cloud.sun.fill", temperature: 22)
                    WeatherDayView(dayOfWeek: "TUE", imageName: "sun.max.fill", temperature: 28)
                    WeatherDayView(dayOfWeek: "WED", imageName: "wind.snow", temperature: 0)
                    WeatherDayView(dayOfWeek: "THU", imageName: "snow", temperature: -3)
                    WeatherDayView(dayOfWeek: "FRI", imageName: "cloud.fill", temperature: 7)
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
