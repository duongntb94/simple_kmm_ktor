package com.duongntb94.kmmnetwork.models

import com.duongntb94.database.DBSong
import kotlinx.serialization.Serializable

@Serializable
data class Song (
    val trackId: Long,
    val artistName: String,
    val trackName: String,
    val artworkUrl100: String
) {
    companion object {
        fun instanceFromDBSong(dbSong: DBSong): Song {
            return Song(dbSong.trackId, dbSong.artistName, dbSong.trackName, dbSong.artworkUrl100);
        }
    }
    override fun toString(): String {
        return "ID: $trackId - name: $trackName - Artist name: $artistName"
    }
}