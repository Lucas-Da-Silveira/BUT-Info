//
//  AlbumInfoViewModel.swift
//  ItunesApp
//
//  Created by da silveira lucas on 12/11/2024.
//

import Foundation

struct AlbumViewModel: Identifiable {
    private let album: AlbumInfo
    
    var collectionName: String { album.collectionName }
    var releaseDate: String { album.releaseDate }
    var artworkUrl: URL { album.artworkUrl100 }
    
    init(album: AlbumInfo) {
        self.album = album
    }
}
