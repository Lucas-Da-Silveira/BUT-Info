//
//  FollowersListView.swift
//  GitFollowers
//
//  Created by da silveira lucas on 19/11/2024.
//

import SwiftUI

struct FollowersListView: View {
    
    @StateObject var viewModel = GitViewModel()
    var userName: String
    
    var body: some View {
        VStack{
            ScrollView{
                //LazyVGrid
                LazyHGrid(rows: /*@START_MENU_TOKEN@*/[GridItem(.fixed(20))]/*@END_MENU_TOKEN@*/, content: {
                })
            }
            
        }
        .task {
            await viewModel.getFollowers(for: userName, page: 1)
        }
    }
}

#Preview {
    FollowersListView(userName: "SAllen0400")
}
