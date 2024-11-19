//
//  ContentView.swift
//  GitFollowers
//
//  Created by da silveira lucas on 19/11/2024.
//

import SwiftUI

struct MainView: View {
    var body: some View {
        TabView{
            SearchView()
                .tabItem {
                    Image(systemName: "magnifyingglass")
                    Text("Search")
                }
            FavoritesView()
                .tabItem {
                    Image(systemName: "star.fill")
                    Text("Favorite")
                }
        }
    }
}

#Preview {
    MainView()
}
