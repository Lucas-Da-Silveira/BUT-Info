//
//  ContentView.swift
//  NasaPhoto
//
//  Created by da silveira lucas on 05/11/2024.
//

import SwiftUI

struct ContentView: View {
    
    @StateObject var viewModel = PhotoInfoViewModel(service: PhotoInfoService())
    
    var body: some View {
        NavigationView{
            switch viewModel.state{
            case .success(let data):
                VStack{
                    AsyncImage(url: data.url){ image in image
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: 300, height: 300)
                    }placeholder: {
                        ProgressView()
                    }
                    Text(data.title).font(.title.italic())
                    Text(data.explanation).font(.caption)
                    Text(data.copyright ?? "No copyright")
                }
                
            case .loading:
                ProgressView()
                
            default:
                EmptyView()
            }
        }
        .task {
            await viewModel.getPhotoInfo()
        }
        
    }
}

#Preview {
    ContentView()
}
