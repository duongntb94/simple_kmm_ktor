package com.duongntb94.kmmnetwork.db

import com.duongntb94.database.AppDatabase
import com.duongntb94.database.DBSong
import com.duongntb94.kmmnetwork.models.Song
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllSongs()
        }
    }

    fun getAllDBSongs(): List<DBSong> {
        return dbQuery.selectAllSong().executeAsList();
    }

    /**
     * Insert one song
     */
    fun insertDBSong(trackId: Long?, trackName: String, artistName: String, artworkUrl100: String) {
        dbQuery.insertSong(trackId, trackName, artistName, artworkUrl100);
    }

    /**
     * Bulk insert songs using Courotine.
     */
    fun insertBulkDBSongs(songs: List<Song>) {
        GlobalScope.launch {
            // Clear cache first
            clearDatabase()
            // Save to cache.
            try {
                dbQuery.transaction {
                    songs.forEach { i ->
                        dbQuery.insertSong(i.trackId, i.trackName, i.artistName, i.artworkUrl100);
                    }
                }
            }
            catch (e: Exception) {
                print("insertBulkDBSongs exception!!");
                print(e);
            }
        }
    }
}