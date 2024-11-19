//
//  SearchView.swift
//  GitFollowers
//
//  Created by da silveira lucas on 19/11/2024.
//

import SwiftUI

struct SearchView: View {
    
    @State var userName: String = ""
    
    var body: some View {
        NavigationView{
            VStack{
                Image("github")
                    .resizable()
                    .frame(width: 250, height: 250)
                    .padding()
                
                TextField("Enter a username", text: $userName)
                    .multilineTextAlignment(.center)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding()
                
                Spacer()
                
                NavigationLink(destination: FollowersListView(userName: userName)){
                    Text("Get Followers")
                        .frame(width: 250, height: 50)
                        .background(Color.accentColor)
                        .cornerRadius(12)
                        .foregroundStyle(.white)
                        .padding(.bottom,60)
                }
            }
        }
    }
}

#Preview {
    SearchView()
}
