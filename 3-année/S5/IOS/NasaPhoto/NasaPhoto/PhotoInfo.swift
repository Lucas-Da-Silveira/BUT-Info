//
//  PhotoInfo.swift
//  NasaPhoto
//
//  Created by da silveira lucas on 05/11/2024.
//

import Foundation

struct PhotoInfo: Codable{
    
    var title: String
    var explanation: String
    var url: URL
    var copyright: String?
    
}
