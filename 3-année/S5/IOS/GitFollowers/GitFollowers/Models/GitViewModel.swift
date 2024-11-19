//
//  GitViewModel.swift
//  GitFollowers
//
//  Created by da silveira lucas on 19/11/2024.
//

import Foundation

class GitViewModel: ObservableObject{
    
    enum State {
        case notAvailable
        case loading
        case success(data: [Follower])
        case failed(error: Error)
    }
    
    @Published var state: State = .notAvailable
    
    private let service: GitService
    
    init(){
        self.service = GitService()
    }
    
    func getFollowers(for username: String, page: Int) async{
        self.state = .loading
        
        do{
            let followers = try await service.fetchFollowers(for: username, page: page)
            self.state = .success(data: followers)
        }catch{
            self.state = .failed(error: error)
            print(error)
        }
    }
    
    
}
