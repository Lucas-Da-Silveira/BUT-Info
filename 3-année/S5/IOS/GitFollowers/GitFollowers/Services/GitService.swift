//
//  GitService.swift
//  GitFollowers
//
//  Created by da silveira lucas on 19/11/2024.
//

import Foundation

struct GitService{
    enum GitError: Error{
        case failed
        case failedToDecode
        case invalidStatusCode
        case invalideUserName
    }
    
    func fetchFollowers(for username: String, page: Int) async throws -> [Follower]{
        let baseURL = "htpps://api.github.com/users/"
        let endpoint = baseURL + "\(username)/followers?per_page=100&page=\(page)"
        
        let url = URL(string: endpoint)!
        
        let (data, response) = try await URLSession.shared.data(from: url)
        
        guard let response = response as? HTTPURLResponse, response.statusCode == 200 else {
            throw GitError.invalidStatusCode
        }
        
        let decodeData = try JSONDecoder().decode([Follower].self, from: data)
        
        return decodeData
    }
}
