import SwiftUI
import shared

class HomeScreenProxy: ObservableObject {
    let itunesStore = ItunesStore()
    
    @Published
    var songs: [Song] = []
    
    func getResultCollection() {
        itunesStore.getResultCollection { [weak self] resultCollection, error in
            if let resultCollection = resultCollection {
                self?.songs = resultCollection.results
            }
        }
    }
    
}



struct HomeScreen: View {
    @ObservedObject
    var proxy = HomeScreenProxy()
    
    var body: some View {
        NavigationView {
            List(proxy.songs, id: \.trackId) { song in
                NavigationLink {
                    DetailScreen(song: song)
                } label: {
                    ListViewItem(title: song.trackName, subtitle: song.artistName, imageUrl: song.artworkUrl100)
                }
            }
            .listStyle(.plain)
            .onAppear(perform: {
                proxy.getResultCollection()
            })
            .navigationTitle(Text("Itunes Song"))
            .navigationBarTitleDisplayMode(.large)
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
	static var previews: some View {
        HomeScreen()
	}
}
