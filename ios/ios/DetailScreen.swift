//
//  DetailScreen.swift
//  ios
//
//  Created by Duong on 2/1/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import Kingfisher

struct DetailScreen: View {
    let song: Song
    
    var body: some View {
        VStack(alignment: HorizontalAlignment.leading) {
            KFImage(URL(string: song.artworkUrl100)!)
                .resizable()
                .frame(width: 300, height: 300, alignment: .center)
                .padding(EdgeInsets(top: 20, leading: 0, bottom: 20, trailing: 0))
            Text(song.trackName).font(.title)
                .padding(EdgeInsets(top: 0, leading: 0, bottom: 5, trailing: 0))
            
            Text(song.artistName).font(.headline)
            Spacer()
        }
        .navigationTitle(song.trackName)
        .navigationBarTitleDisplayMode(.inline)
    }
}

struct DetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        let song = Song(trackId: 1, artistName: "Taylor Swift", trackName: "Duong", artworkUrl100: "https://is1-ssl.mzstatic.com/image/thumb/Music125/v4/ce/a2/bd/cea2bdd1-e7b4-e936-1869-dae6f3c92bd7/source/100x100bb.jpg")
        DetailScreen(song: song)
    }
}



