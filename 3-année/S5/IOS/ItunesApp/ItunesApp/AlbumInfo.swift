//
//  AlbumInfo.swift
//  ItunesApp
//
//  Created by da silveira lucas on 12/11/2024.
//

import Foundation

struct AlbumInfo: Decodable, Hashable{
    let collectionName: String
    let releaseDate: String
    let artworkUrl100: URL
}

struct AlbumResponse: Decodable{
    var results: [AlbumInfo]
}
