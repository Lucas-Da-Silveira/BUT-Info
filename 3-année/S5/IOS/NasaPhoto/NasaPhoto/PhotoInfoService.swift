//
//  PhotoInfoService.swift
//  NasaPhoto
//
//  Created by da silveira lucas on 05/11/2024.
//

import Foundation

struct PhotoInfoService {
    enum PhotoInfoError: Error{
        case failed
        case failedToDecode
        case invalidStatusCode
    }
    //abONaFIip0FrAmEcZLiXbZqIUw2r7dOUPmRFWZMN
    func fetchPhotoInfo() async throws -> PhotoInfo{
        let url = URL(string: "https://api.nasa.gov/planetary/apod?api_key=abONaFIip0FrAmEcZLiXbZqIUw2r7dOUPmRFWZMN&date=2022-01-09")!
        
        let (data, response) = try await URLSession.shared.data(from: url)
        
        guard let response = response as? HTTPURLResponse, response.statusCode == 200 else{
            throw PhotoInfoError.invalidStatusCode
        }
        
        let decodeData = try JSONDecoder().decode(PhotoInfo.self, from: data)
        
        return decodeData
    }
}
