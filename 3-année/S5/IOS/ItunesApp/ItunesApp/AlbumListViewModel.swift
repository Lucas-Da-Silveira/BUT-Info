//
//  AlbumListViewModel.swift
//  ItunesApp
//
//  Created by da silveira lucas on 12/11/2024.
//

import Foundation

class AlbumListViewModel: ObservableObject {
    enum State {
        case notAvailable
        case loading
        case success(data: [AlbumViewModel])
        case failed(error: Error)
    }
    
    @Published var state: State = .notAvailable
    @Published var hasError: Bool = false
    
    private let service: AlbumInfoService
    
    init(service: AlbumInfoService) {
        self.service = service
    }
    
    func loadAlbums(for artist: String) async {
        self.state = .loading
        do {
            let albums = try await service.fetchAlbumInfo(for: artist)
            let albumViewModels = albums.map { AlbumViewModel(album: $0) }
            self.state = .success(data: albumViewModels)
        } catch {
            self.state = .failed(error: error)
            self.hasError = true
            print("Erreur de chargement des albums : \(error)")
        }
    }
}
