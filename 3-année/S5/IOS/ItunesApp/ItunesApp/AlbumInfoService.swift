//
//  AlbumInfoService.swift
//  ItunesApp
//
//  Created by da silveira lucas on 12/11/2024.
//

import Foundation

struct AlbumInfoService{
    enum AlbumInfoError: Error{
        case failed
        case failedToDecode
        case invalidStatusCode
    }
    
    func fetchAlbumInfo(for artist: String) async throws -> [AlbumInfo]{
        let formattedArtist = artist.replacingOccurrences(of: " ", with: "+")
        let urlString = "https://itunes.apple.com/search?term=\(formattedArtist)&limit=25&entity=album"
        
        guard let url = URL(string: urlString) else {
            throw AlbumInfoError.failed
        }
        let (data, response) = try await URLSession.shared.data(from: url)
        
        guard let response = response as? HTTPURLResponse, response.statusCode == 200 else {
            throw AlbumInfoError.invalidStatusCode
        }
        
        do {
            let decodeData = try JSONDecoder().decode(AlbumResponse.self, from: data)
            return decodeData.results
        } catch {
            throw AlbumInfoError.failedToDecode
        }
    }
}
