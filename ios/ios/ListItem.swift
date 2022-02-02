//
//  ListViewItem.swift
//  ios
//
//  Created by Duong on 2/1/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Kingfisher

struct ListViewItem: View {
    
    var title : String
    var subtitle : String
    var imageUrl : String
    
    var body: some View {
        HStack(alignment: .center, spacing: nil) {
            KFImage(URL(string: imageUrl)!)
                .resizable()
                .frame(width: 50, height: 50, alignment: .center)
            VStack(alignment: .leading, spacing: nil) {
                Text(title)
                    .font(.headline)
                Text(subtitle)
                    .font(.subheadline)
            }
            Spacer()
        }
        .padding()
    }
}

struct ListViewItem_Previews: PreviewProvider {
    static var previews: some View {
        ListViewItem(title: "Love Story", subtitle: "Taylor Swift", imageUrl: "https://picsum.photos/200/200")
    }
}

