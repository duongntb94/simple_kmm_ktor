import SwiftUI
import shared

class HomeScreenProxy: ObservableObject {
    let itunesStore = ItunesStore(databaseDriverFactory: DatabaseDriverFactory())
    
    @Published
    var songs: [Song] = []
    
    // Modify this variable
    @Published
    var fromCache: Bool = false {
        didSet {
            getResultCollection()
        }
    }
    
    func getResultCollection() {
        itunesStore.getResultCollection(fromCache: fromCache, completionHandler: { [weak self] resultCollection, error in
            if let resultCollection = resultCollection {
                self?.songs = resultCollection.results.sorted(by: { s1, s2 in
                    s1.trackId > s2.trackId
                })
            }
        })
    }
    
}


struct HomeScreen: View {
    @ObservedObject
    var proxy = HomeScreenProxy()
    
    var body: some View {
        NavigationView {
            VStack {
                if (proxy.fromCache) {
                    Text("Data is from cache")
                        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: 40)
                        .font(.subheadline)
                        .foregroundColor(.white)
                        .background(Color.red)
                }
                
                List(proxy.songs, id: \.trackId) { song in
                    NavigationLink {
                        DetailScreen(song: song)
                    } label: {
                        ListViewItem(title: song.trackName, subtitle: song.artistName, imageUrl: song.artworkUrl100)
                    }
                }
                .listStyle(.plain)
                .navigationTitle(Text("Itunes Song"))
                .navigationBarTitleDisplayMode(.large)
                .toolbar {
                    ToolbarItem {
                        Button {
                            proxy.fromCache = !proxy.fromCache
                        } label: {
                            Image(systemName: proxy.fromCache ? "wifi.slash" : "wifi")
                                .foregroundColor(proxy.fromCache ? .red : .green)
                        }

                    }
                }
            }
        }.onAppear {
            proxy.getResultCollection()
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
	static var previews: some View {
        HomeScreen()
	}
}
