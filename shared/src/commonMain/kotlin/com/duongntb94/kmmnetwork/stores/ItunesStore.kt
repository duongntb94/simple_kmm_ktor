package com.duongntb94.kmmnetwork.stores

import ResultCollection
import com.duongntb94.database.DBSong
import com.duongntb94.kmmnetwork.db.Database
import com.duongntb94.kmmnetwork.db.DatabaseDriverFactory
import com.duongntb94.kmmnetwork.models.Song
import com.duongntb94.kmmnetwork.services.ItunesService
import com.duongntb94.kmmnetwork.services.impl.ItunesServiceImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ItunesStore(databaseDriverFactory: DatabaseDriverFactory) {
    var resultCollection: ResultCollection = ResultCollection(0);
    var selectedSong: Song? = null
    var database: Database = Database(databaseDriverFactory);

    private val itunesService: ItunesService = ItunesServiceImpl();

    /**
     * Fetch collection of songs.
     * From DB: true, fetch result fro
     */
    suspend fun getResultCollection(fromCache: Boolean): ResultCollection {
        resultCollection = if (fromCache) {
            // Get data from cache mode.
            val dbSongs = database.getAllDBSongs();
            val songs = dbSongs.map { i -> Song.instanceFromDBSong(i) }
            ResultCollection(dbSongs.size.toLong(), songs);
        } else {
            // call two apis parallel
            val resultCollection = itunesService.getParallelResultCollection();
            // Save data to cache for later use.
            database.insertBulkDBSongs(resultCollection.results);
            return resultCollection
        }
        return resultCollection;
    }

    /**
     * Set selected song based on selected index.
     */
    fun setSelectedSong(id: Int) {
        if (id < 0 || id >= resultCollection.resultCount) {
            // TODO: Throw index not found
            return;
        }
        selectedSong = resultCollection.results[id];
    }
}