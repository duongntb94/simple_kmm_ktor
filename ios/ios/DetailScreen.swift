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
        let song = Song(wrapperType: "track", kind: "song", artistId: 1, collectionId: 1, trackId: 1, artistName: "Panic! At the Disco", collectionName: "Death of a Bachelor", trackName: "Victorious", collectionCensoredName: "Death of a Bachelor", trackCensoredName: "Victorious", artistViewUrl: "https://music.apple.com/us/artist/panic-at-the-disco/80456331?uo=4", collectionViewUrl: "https://music.apple.com/us/album/victorious/1050385369?i=1050385377&uo=4", trackViewUrl: "https://music.apple.com/us/album/victorious/1050385369?i=1050385377&uo=4", previewUrl: "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview125/v4/e7/20/e0/e720e003-570e-547f-b730-d83a80bb282c/mzaf_6553745391489825828.plus.aac.p.m4a", artworkUrl30: "https://is1-ssl.mzstatic.com/image/thumb/Music125/v4/ce/a2/bd/cea2bdd1-e7b4-e936-1869-dae6f3c92bd7/source/30x30bb.jpg", artworkUrl60: "https://is1-ssl.mzstatic.com/image/thumb/Music125/v4/ce/a2/bd/cea2bdd1-e7b4-e936-1869-dae6f3c92bd7/source/60x60bb.jpg", artworkUrl100: "https://is1-ssl.mzstatic.com/image/thumb/Music125/v4/ce/a2/bd/cea2bdd1-e7b4-e936-1869-dae6f3c92bd7/source/100x100bb.jpg", collectionPrice: 0.0, trackPrice: 0.0, releaseDate: "2015-09-29T07:00:00Z", collectionExplicitness: "explicit", trackExplicitness: "notExplicit", discCount: 1, discNumber: 1, trackCount: 11, trackNumber: 1, trackTimeMillis: 178594, country: "USA", currency: "USD", primaryGenreName: "Alternative", isStreamable: true, contentAdvisoryRating: nil, collectionArtistName: nil, collectionArtistID: nil, collectionArtistViewURL: nil)
        DetailScreen(song: song)
    }
}



