//
//  PhotoInfoViewModel.swift
//  NasaPhoto
//
//  Created by da silveira lucas on 05/11/2024.
//

import Foundation

class PhotoInfoViewModel: ObservableObject{
    enum State {
        case notAvailable
        case loading
        case success(data: PhotoInfo)
        case failed(error: Error)
    }
    
    @Published var state: State = .notAvailable
    @Published var hasError: Bool = false
    
    private let service: PhotoInfoService
    
    init(service: PhotoInfoService){
        self.service = service
    }
    
    func getPhotoInfo() async{
        self.state = .loading
        do {
            let photoInfo = try await service.fetchPhotoInfo()
            self.state = .success(data: photoInfo)
        }catch{
            self.state = .failed(error: error)
            self.hasError = true
            print(error)
        }
    }
    
    
}
